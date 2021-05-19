import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmota.playground.triathlon.shared.ServiceLocator
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.shared.presentation.cb.IConferenceData
import com.cmota.playground.triathlon.theme.*
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.imageio.ImageIO
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

private lateinit var events: MutableState<List<Conference>>

@ExperimentalFoundationApi
fun main() = Window {

  events = remember { mutableStateOf(emptyList()) }

  ServiceLocator.getConferencePresenter.attachView(object : IConferenceData {
    override fun onConferenceDataFetched(conferences: List<Conference>) {
      events.value = conferences
    }

    override fun onConferenceDataFailed(e: Exception) {
      //Do nothing
    }
  })

  Window("Triathlon") {
    Surface(modifier = Modifier.fillMaxSize()) {
      MaterialTheme {
        Box(
          modifier = Modifier.fillMaxSize()
            .background(color = Color(180, 180, 180))
            .padding(10.dp)
        ) {

          val state = rememberLazyListState()

          LazyColumn(
            content = {
              items(events.value) {
                AddConference(conference = it)
              }
            },
            state = state
          )

          VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
              scrollState = state,
              itemCount = events.value.size,
              averageItemSize = 150.dp
            )
          )
        }
      }
    }
  }
}

@Composable
fun AddConference(conference: Conference) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
    backgroundColor = Color.White,
    shape = RoundedCornerShape(8.dp)
  ) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {

      val logo = loadNetworkImage(conference.logo)

      if (logo != null) {
        Image(
          modifier = Modifier.size(75.dp),
          bitmap = logo,
          contentDescription = null,
          contentScale = ContentScale.Crop
        )
      } else {
        Box(
          modifier = Modifier
            .size(75.dp)
            .background(colorPrimaryDark),
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1.0f)
      ) {
        Text(
          text = conference.name,
          style = typography.h1
        )

        Text(
          text = conference.city,
          style = typography.h2
        )

        Text(
          text = conference.date,
          style = typography.h3
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      val backgroundColor = if (conference.isCanceled()) {
        colorNeutral
      } else {
        defaultAndroid
      }

      Row(
        modifier = Modifier.weight(0.75f),
        horizontalArrangement = Arrangement.End
      ) {
        Card(
          modifier = Modifier.width(100.dp),
          backgroundColor = backgroundColor,
          shape = RoundedCornerShape(8.dp)
        ) {

          Text(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp, bottom = 8.dp),
            text = conference.status,
            textAlign = TextAlign.Center,
            style = typography.h4
          )
        }

        Spacer(modifier = Modifier.width(16.dp))
      }
    }
  }
}

fun loadNetworkImage(link: String): ImageBitmap? {
  try {
    val url = URL(link)
    val connection = url.openConnection() as HttpsURLConnection
    connection.hostnameVerifier = HostnameVerifier { _, _ -> true }
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return org.jetbrains.skija.Image.makeFromEncoded(byteArray).asImageBitmap()
  } catch (e: Exception) {
    return null
  }
}