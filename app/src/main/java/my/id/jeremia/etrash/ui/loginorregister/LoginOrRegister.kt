package my.id.jeremia.etrash.ui.loginorregister

import android.graphics.BitmapFactory
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun LoginOrRegister(modifier: Modifier = Modifier, viewModel: LoginOrRegisterViewModel) {
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.background)
    val bitmap4 = BitmapFactory.decodeResource(context.resources, R.drawable.onboarding4)

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
                    bitmap = bitmap4.asImageBitmap(),
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
                    "Mari Mulai!",
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
                    "Untuk memulai kamu butuh akun terlebih dulu.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.White,
                )



                Button(
                    onClick = {
                        viewModel.login();
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // replace with your button color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Masuk", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Daftar button
                OutlinedButton(
                    onClick = {
                        viewModel.register();
                    },
                    border = BorderStroke(
                        1.dp,
                        Color(0xFF4CAF50)
                    ), // replace with your outline button color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Daftar",
                        color = Color(0xFFFFFFFF)
                    ) // replace with your text color
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Link
                TextButton(onClick = { /* Handle forgot login */ }) {
                    Text(
                        text = "Tidak bisa masuk?",
                        color = Color(0xFF4CAF50)
                    ) // replace with your link color
                }
            }

        }


    }
}
