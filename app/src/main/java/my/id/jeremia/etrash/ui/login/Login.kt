package my.id.jeremia.etrash.ui.login

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.theme.InterFontFamily

@Composable
fun Login(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    LoginView(modifier,
        email = viewModel.email.collectAsStateWithLifecycle().value,
        password = viewModel.password.collectAsStateWithLifecycle().value,
        emailError = viewModel.emailError.collectAsStateWithLifecycle().value,
        passwordError = viewModel.passwordError.collectAsStateWithLifecycle().value,
        enableLoginButton = viewModel.enableLoginButton.collectAsStateWithLifecycle().value,
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        basicLogin = { viewModel.doLogin() },
        navRegister = { viewModel.navRegister() })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    emailError: String,
    passwordError: String,
    enableLoginButton: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    basicLogin: () -> Unit,
    navRegister: () -> Unit,
) {

    val context = LocalContext.current
    val bitmap by remember {
        mutableStateOf(
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.background
            )
        )
    }

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
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                stringResource(R.string.login_header),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                ),
                color = Color(0xFFFFFFFF),
            )

            Text(
                stringResource(R.string.login_subheader),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                ),
                color = Color(0xFFFFFFFF),
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                value = email,
                onValueChange = onEmailChange,
                placeholder = { Text("Email") },
                singleLine = true,
                isError = emailError.isNotEmpty(),
                supportingText = {
                    Text(
                        text = emailError,
                        modifier = Modifier
                            .background(Color.White)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    errorContainerColor = Color.White,
                ),

                )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                value = password,
                onValueChange = onPasswordChange,
                placeholder = { Text("Kata sandi") },
                singleLine = true,
                isError = passwordError.isNotEmpty(),
                supportingText = {
                    Text(
                        text = passwordError,
                        modifier = Modifier
                            .background(Color.White)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    errorContainerColor = Color.White,
                ),
            )

            Button(
                onClick = basicLogin,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // replace with your button color
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Masuk", color = Color.White)
            }

        }


    }
}


@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    LoginView(
        email = "",
        password = "",
        emailError = "",
        passwordError = "",
        onEmailChange = {},
        onPasswordChange = {},
        basicLogin = {},
        navRegister = {},
        enableLoginButton = true,
    )
}