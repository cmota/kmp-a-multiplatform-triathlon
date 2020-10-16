import com.cmota.playground.triathlon.shared.ServiceLocator
import com.cmota.playground.triathlon.shared.data.entities.Conference
import com.cmota.playground.triathlon.shared.presentation.cb.IConferenceData
import kotlinx.css.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import styled.*

fun main() {

    ServiceLocator.getConferencePresenter.attachView(object : IConferenceData {
        override fun onConferenceDataFetched(conferences: List<Conference>) {
            render(kotlinx.browser.document.getElementById("root")) {

                h1 {
                    +"Conferences"
                }

                conferences.forEach { item ->

                    styledDiv {
                        css {
                            display = Display.flex
                            alignItems = Align.center
                            flexGrow = 1.0
                        }
                        div {
                            styledImg(
                                src = item.logo
                            ) {
                                css {
                                    objectFit = ObjectFit.cover
                                    width = 75.px
                                }
                            }
                        }

                        styledDiv {
                            css {
                                marginLeft = 10.px
                                display = Display.inline
                                flexGrow = 1.0
                            }
                            styledP {
                                css {
                                    fontSize = 17.px
                                    fontWeight = FontWeight.bold
                                    marginTop = 0.em
                                    marginBottom = 0.em
                                }
                                +item.name
                            }

                            styledP {
                                css {
                                    fontSize = 15.px
                                    color = Color.darkGray
                                    marginTop = 0.em
                                    marginBottom = 0.em
                                }
                                +item.country
                            }

                            styledP {
                                css {
                                    fontSize = 15.px
                                    marginTop = 0.em
                                    marginBottom = 0.em
                                }
                                +item.date
                            }
                        }

                        styledDiv {
                            css {
                                display = Display.flex
                                alignItems = Align.flexEnd
                            }
                            styledP {
                                css {
                                    fontSize = 15.px
                                    marginTop = 0.em
                                    marginBottom = 0.em
                                    flexGrow = 0.0
                                }
                                +item.status
                            }
                        }
                    }
                }
            }
        }

        override fun onConferenceDataFailed(e: Exception) {
            render(kotlinx.browser.document.getElementById("root")) {
                child(functionalComponent<RProps> { _ ->

                    h1 {
                        +"Unable to fetch data. Error: $e"
                    }
                })
            }
        }
    })
}