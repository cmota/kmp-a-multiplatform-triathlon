import androidx.compose.runtime.Composable
import androidx.compose.web.css.*
import androidx.compose.web.elements.Div
import androidx.compose.web.elements.Img
import androidx.compose.web.elements.P
import androidx.compose.web.elements.Text
import androidx.compose.web.renderComposable
import com.cmota.playground.triathlon.shared.ServiceLocator
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.shared.presentation.cb.IConferenceData
import org.jetbrains.compose.common.core.graphics.Color
import org.jetbrains.compose.common.foundation.layout.Box
import org.jetbrains.compose.common.ui.Modifier
import org.jetbrains.compose.common.ui.background

fun main() {
    ServiceLocator.getConferencePresenter.attachView(object : IConferenceData {
        override fun onConferenceDataFetched(conferences: List<Conference>) {
            renderComposable(rootElementId = "root") {

                conferences.forEach { item ->

                    AddConference(
                            conference = item
                    )
                }

            }
        }

        override fun onConferenceDataFailed(e: Exception) {
            renderComposable(rootElementId = "root") {
                Text("Unable to fetch data. Error: $e")
            }
        }
    })
}

@Composable
fun AddConference(conference: Conference) {
    /* Not working with current compose release
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
    */

    Div(style = {
        padding(16.px)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        flexGrow(1.0)
    }) {

        Div {
            Img(
                    src = conference.logo,
                    style = {
                        width(75.px)
                        height(75.px)
                        property("object-fit", value("cover"))
                    }
            )
        }

        Div(style = {
            marginLeft(10.px)
            display(DisplayStyle.Inline)
            flexGrow(1.0)
        }){

            P(style = {
                fontSize(17.px)
                margin(0.px)
            }) {
                Text(conference.name)
            }

            P(style = {
                fontSize(15.px)
                margin(0.px)
            }) {
                Text(conference.city)
            }

            P(style = {
                fontSize(15.px)
                margin(0.px)
            }) {
                Text(conference.date)
            }
        }

        Div(style = {
            display(DisplayStyle.Flex)
            alignContent(AlignContent.End)
        }) {
            Box(
                    modifier = Modifier
                            .background(Color.Green),
                    content = {
                        Text(conference.status)
                    }
            )
        }

/*val backgroundColor = if (conference.isCanceled()) {
    colorNeutral
} else {
    defaultAndroid
}


    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
    ) {
      Text(conference.status)
    }*/
    }
//}

}