package my.id.jeremia.etrash.ui.onboard

import android.graphics.BitmapFactory
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.theme.InterFontFamily

@Composable
fun OnBoard(modifier: Modifier = Modifier, viewModel: OnBoardViewModel) {
    val context = LocalContext.current
    val bitmap by remember { mutableStateOf(BitmapFactory.decodeResource(context.resources, R.drawable.background))}
    val logo by remember { mutableStateOf(BitmapFactory.decodeResource(context.resources, R.drawable.logo))}
    val bitmap2 by remember { mutableStateOf(BitmapFactory.decodeResource(context.resources, R.drawable.onboarding2))}
    val bitmap3 by remember { mutableStateOf( BitmapFactory.decodeResource(context.resources, R.drawable.onboarding3))}

    var onBoardProgress by remember { mutableStateOf(1) }
    val bitmapToShow by remember {
        derivedStateOf {
            when (onBoardProgress) {
                2 -> bitmap2.asImageBitmap()
                3 -> bitmap3.asImageBitmap()
                else -> logo.asImageBitmap()
            }
        }
    }

    val title by remember {
        derivedStateOf {
            when (onBoardProgress) {
                2 -> "Antar sampahmu di E-Trash Clinic"
                3 -> "Unggah Sampahmu!"
                else -> "Selamat Datang di E-Trash Clinic"
            }
        }
    }

    val subtitle by remember {
        derivedStateOf {
            when (onBoardProgress) {
                2 -> "Sekarang kamu bisa memilah dan menimbang sampah yang kamu kumpulkan di E-trash Clinic."
                3 -> "Pilah, Timbang, dan unggah sampahmu di aplikasi E-Trash Clinic lalu dapatkan koin di setiap unggahan sampahmu yang bisa kamu tukarkan dengan uang atau kerajinan hasil dari pemanfaatan sampahmu."
                else -> "Aplikasi pengolahan sampah untuk menjadikan lingkungan yang bersih."
            }
        }
    }

    LaunchedEffect(key1 = onBoardProgress) {
        if (onBoardProgress >= 4) {
            viewModel.setCompleted()
        }
    }


    val infiniteTransition = rememberInfiniteTransition()

    val logoTranslateOffset by infiniteTransition.animateFloat(
        initialValue = 25f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500), RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        if (onBoardProgress <= 3) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        WindowInsets.systemBars.asPaddingValues()
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Column(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {

                    Image(
                        bitmap = bitmapToShow,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                this.translationY = logoTranslateOffset
                            },
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.BottomCenter,
                    )


                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {

                    Text(
                        title,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 32.sp,
                        ),
                        lineHeight = 1.2.em,
                        textAlign = TextAlign.Left,
                    )


                    Text(
                        subtitle,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                        ),
                        textAlign = TextAlign.Left,
                        color = Color.White,
                    )


                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight(0.3f)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Row {
                            repeat(3) {
                                DotIndicator(isSelected = it + 1 == onBoardProgress)
                            }
                        }

                        Row {

                            if (onBoardProgress != 1) {
                                IconButton(onClick = {
                                    onBoardProgress -= 1;
                                }) {
                                    Icon(
                                        Icons.Default.KeyboardArrowLeft,
                                        contentDescription = "Previous",
                                        tint = Color.White,
                                        modifier = Modifier.size(100.dp)
                                    )
                                }
                            }

                            IconButton(onClick = {
                                onBoardProgress += 1;
                            }) {
                                Icon(
                                    Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Next",
                                    tint = Color.White,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }

                    }


                }
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        WindowInsets.systemBars.asPaddingValues()
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                )
            }
        }

    }
}


@Composable
fun DotIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .padding(5.dp)
            .background(
                color = if (isSelected) Color.White else Color.Gray, shape = CircleShape
            )
    )
}
