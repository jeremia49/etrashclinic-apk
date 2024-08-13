package my.id.jeremia.etrash.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R

@Composable
fun Login(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    LoginView(
        modifier,
        email = viewModel.email.collectAsStateWithLifecycle().value,
        password = viewModel.password.collectAsStateWithLifecycle().value,
        emailError = viewModel.emailError.collectAsStateWithLifecycle().value,
        passwordError = viewModel.passwordError.collectAsStateWithLifecycle().value,
        enableLoginButton=viewModel.enableLoginButton.collectAsStateWithLifecycle().value,
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        basicLogin = { viewModel.dologin() },
        navRegister = {viewModel.navRegister()}
    )
}


@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    emailError: String,
    passwordError: String,
    enableLoginButton:Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    basicLogin: () -> Unit,
    navRegister: ()->Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {


        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            Column(
                modifier = modifier
                    .padding(
                        top = 10.dp
                    )
            ) {

                Text(
                    stringResource(R.string.login_header),
                        fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
//                    color = Color(0xFF000000),
                )
                Text(
                    stringResource(R.string.login_subheader),
                        fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
//                    color = Color(0xFF999EA1),
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 5.dp,
                    bottom = 4.dp
                )
            ) {
                Text(
                    "Email",
                        fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = { Text("Email anda") },
                    singleLine = true,
                    isError = emailError.isNotEmpty(),
                    supportingText = {
                        Text(text = emailError)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                )

                Text(
                    "Password",
                        fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = onPasswordChange,
                    placeholder = { Text("Password") },
                    singleLine = true,
                    isError = passwordError.isNotEmpty(),
                    supportingText = {
                        Text(text = passwordError)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top=15.dp,
                            bottom = 20.dp
                        ),
                    onClick = basicLogin,
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color.Black
                    ),
                    enabled = enableLoginButton,
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                        )
                    )
                }

//                Box(
//                    modifier,
//                    contentAlignment = Alignment.Center
//                ) {
//                    HorizontalDivider(
//                        modifier = modifier,
//                        thickness = 1.dp,
//                    )
//                    Text(
//                        "atau",
//                        modifier = modifier
//                            .background(MaterialTheme.colorScheme.background)
//                            .padding(horizontal = 10.dp),
//                        textAlign = TextAlign.Center,
//                    )
//                }


//                OutlinedButton(
//                    onClick = { /*TODO*/ },
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(top = 20.dp),
//                    colors = ButtonDefaults.buttonColors().copy(
//                        containerColor = Color.Black
//                    )
//                ) {
//                    Row(
//                        modifier = modifier,
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.icons8_google),
//                            contentDescription = "Login dengan Google",
//                            modifier = modifier
//                                .padding(vertical = 8.dp)
//                                .size(20.dp)
//                        )
//
//                        Text(
//                            "Login with Google",
//                            modifier = modifier
//                                .padding(8.dp)
//                                .fillMaxWidth(),
//                            textAlign = TextAlign.Center,
//
//                            )
//                    }
//
//                }
            }





            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Belum ada akun ? ",
                    style = TextStyle(
//                        color = grey,
                                fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    )
                )
                TextButton(onClick = { navRegister() }) {
                    Text(
                        "Daftar",
                        style = TextStyle(
//                            color = Color.Black,
                                        fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                        )
                    )
                }
            }


//            Button(onClick = {}) {
////                Image(
////                    painter = painterResource(id = R.drawable.icons8_google),
////                    contentDescription = "Login with Google",
////                    size
////                )
//
//            }


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