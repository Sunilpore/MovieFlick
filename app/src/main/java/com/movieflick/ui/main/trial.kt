package com.movieflick.ui.main

import android.content.res.Configuration
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import com.movieflick.R

import com.movieflick.ui.theme.Dark
import com.movieflick.ui.theme.Light
import com.movieflick.ui.theme.MovieFlickTheme
import com.movieflick.utils.preview.PreviewContainer


@Composable
fun ConstUI(){

    MovieFlickTheme {

        Column(modifier = Modifier.fillMaxSize()) {

            val constraints = ConstraintSet{

                val yellowBox = createRefFor("yellowBox")
                val redBox = createRefFor("redBox")

                constrain(yellowBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)

                }


                constrain(redBox){
                    top.linkTo(parent.top)
                    bottom.linkTo(yellowBox.bottom)
                    start.linkTo(yellowBox.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.fillToConstraints
                }

                //--------------------------------------------//

                val greenBox = createRefFor("greenbox")
                val magentaBox = createRefFor("magentabox")
                val guidelines = createGuidelineFromTop(0.5f)

                constrain(greenBox) {
                    top.linkTo(guidelines)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(magentaBox) {
                    top.linkTo(greenBox.top)
                    bottom.linkTo(greenBox.bottom)
                    start.linkTo(greenBox.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.fillToConstraints
                }

                createHorizontalChain(greenBox,magentaBox, chainStyle = ChainStyle.Spread)

            }


            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(Color.Yellow)
                    .layoutId("yellowBox"))

                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox"))

                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox"))

                Box(modifier = Modifier
                    .background(Color.Magenta)
                    .layoutId("magentabox"))

             }

        }

    }

}


@Composable
fun GradientDivider(
    modifier: Modifier,
    color: Color,
    reverse: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = if (reverse) {
                        listOf(Color.Transparent, color)
                    } else {
                        listOf(color, Color.Transparent)
                    }
                )
            )
    )
}


@Composable
fun BlurryGradientDivider(
    modifier: Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Transparent,
                        0.15f to color.copy(alpha = 0.3f),
                        0.5f to color.copy(alpha = 1f),
                        0.85f to color.copy(alpha = 0.3f),
                        1.0f to Color.Transparent
                    )
                )
            )
    )
}



@Composable
fun OrWithVerticalGradientDivider(
    modifier: Modifier = Modifier,
    lineWidth: Dp = 1.dp,
    lineColor: Color = Color.LightGray,
    orText: String = "OR"
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top divider

        BlurryGradientDivider(modifier = Modifier
            .weight(1f)
            .width(1.dp),
            color = Color.LightGray
        )

        // OR text
        Text(
            text = orText,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        // Bottom divider
        GradientDivider(
            modifier = Modifier
                .weight(1f)
                .width(lineWidth),
            color = lineColor,
            reverse = true
        )
    }
}





/*@Preview(Light)
@Preview(Dark, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConstUIPreview(){

    PreviewContainer {
        Surface {
            OrWithVerticalGradientDivider()
        }
    }
}*/


@Preview(Light, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConstSubComposeView(){

    PreviewContainer {
        Surface {

            ConstraintLayout{


                val (dividerLine, orText) = createRefs()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .wrapContentHeight()
                        .background(Color(0xFFF8F8F8))
                        .constrainAs(dividerLine) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.chrome_vert_divider_291448),
                        contentDescription = null,
                        modifier = Modifier
                            .height(280.dp)
                            .width(1.dp),
                        contentScale = ContentScale.FillBounds
                    )

                }



                Text(
                    text = "OR",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier.constrainAs(orText) {
                        start.linkTo(dividerLine.start)
                        end.linkTo(dividerLine.end)
                        centerVerticallyTo(dividerLine)
                    }
                )


            }

            /*Column(
                Modifier
                    .fillMaxSize(1f)
                    .padding(1f.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "OR",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF9C27B0))
                ) {
                    SubcomposeAsyncImage(
                        model = R.drawable.chrome_vert_divider_291448,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(280.dp)
                            .fillMaxWidth(1f)
                    )
                }
            }*/
        }
    }
}