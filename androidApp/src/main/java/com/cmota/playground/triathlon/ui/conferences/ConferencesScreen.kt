package com.cmota.playground.triathlon.ui.conferences

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmota.playground.triathlon.ConferencesViewModel
import com.cmota.playground.triathlon.theme.colorPrimaryDark
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.R
import com.cmota.playground.triathlon.theme.colorNeutral
import com.cmota.playground.triathlon.theme.defaultAndroid
import com.cmota.playground.triathlon.theme.typography
import com.cmota.playground.triathlon.shared.Gutenberg
import dev.chrisbanes.accompanist.coil.CoilImage

private const val TAG = "ConferencesScreen"

@Composable
fun ConferencesScreen(viewModel: ConferencesViewModel,
                      openUrl: (website: String) -> Unit) {
  
  val conferences: List<Conference> by viewModel.conferences.observeAsState(emptyList())
  Gutenberg.d(TAG, "Conferences=${conferences.size}")
  
  Column {
    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
      content = {
        items(conferences) {
          AddConference(it, openUrl)
        }
      }
    )

    Spacer(modifier = Modifier.height(8.dp))
  }
}

@Composable
fun AddConference(conference: Conference,
                  openUrl: (website: String) -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
      .clickable {
      openUrl(conference.website)
    },
    backgroundColor = Color.White,
    shape = RoundedCornerShape(8.dp)
  ) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {

      CoilImage(
        data = conference.logo,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.conference_logo),
        modifier = Modifier
          .size(75.dp)
          .clip(RoundedCornerShape(4.dp)),
        loading = {
          Box(
            Modifier
              .size(75.dp)
              .background(colorPrimaryDark)
          )
        },
        error = {
          Box(
            Modifier
              .size(75.dp)
              .background(Color.Red)
          )
        }
      )

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