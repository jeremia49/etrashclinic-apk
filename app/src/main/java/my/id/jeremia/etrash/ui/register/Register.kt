package my.id.jeremia.etrash.ui.register

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
fun Register(modifier: Modifier = Modifier, viewModel: RegisterViewModel) {
    RegisterView(
        modifier,
        name = viewModel.name.collectAsStateWithLifecycle().value,
        email = viewModel.email.collectAsStateWithLifecycle().value,
        password = viewModel.password.collectAsStateWithLifecycle().value,
        passwordConfirm = viewModel.passwordConfirm.collectAsStateWithLifecycle().value,
        nohp = viewModel.nohp.collectAsStateWithLifecycle().value,
        tandc = viewModel.tandc.collectAsStateWithLifecycle().value,
        nameError = viewModel.nameError.collectAsStateWithLifecycle().value,
        emailError = viewModel.emailError.collectAsStateWithLifecycle().value,
        passwordError = viewModel.passwordError.collectAsStateWithLifecycle().value,
        passwordConfirmError = viewModel.passwordConfirmError.collectAsStateWithLifecycle().value,
        nohpError = viewModel.nohpError.collectAsStateWithLifecycle().value,
        tandcError = viewModel.tandcError.collectAsStateWithLifecycle().value,
        enableRegisterButton = viewModel.enableRegisterButton.collectAsStateWithLifecycle().value,
        onNameChange = { viewModel.onNameChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onPasswordConfirmChange = { viewModel.onPasswordConfirmationChange(it) },
        onNohpChange = { viewModel.onNohpChange(it) },
        onTandcChange = { viewModel.onTandcChange(it) },
        register = { viewModel.doRegister() },
        navLogin = { viewModel.navLogin() },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    passwordConfirm: String,
    nohp: String,
    tandc: Boolean,
    nameError: String,
    emailError: String,
    passwordError: String,
    passwordConfirmError: String,
    nohpError: String,
    tandcError: String,
    enableRegisterButton: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onNohpChange: (String) -> Unit,
    onTandcChange: (Boolean) -> Unit,
    register: () -> Unit,
    navLogin: () -> Unit,
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
                stringResource(R.string.register_header),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                ),
                color = Color(0xFFFFFFFF),
            )

            Text(
                stringResource(R.string.register_subheader),
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
                value = name,
                onValueChange = onNameChange,
                placeholder = { Text("Nama Lengkap") },
                singleLine = true,
                isError = nameError.isNotEmpty(),
                supportingText = {
                    Text(text = nameError)
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
                value = email,
                onValueChange = onEmailChange,
                placeholder = { Text("Email") },
                singleLine = true,
                isError = emailError.isNotEmpty(),
                supportingText = {
                    Text(text = emailError)
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
                value = nohp,
                onValueChange = onNohpChange,
                placeholder = { Text("Nomor telepon") },
                singleLine = true,
                isError = nohpError.isNotEmpty(),
                supportingText = {
                    Text(text = nohpError)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
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
                    Text(text = passwordError)
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


            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                value = passwordConfirm,
                onValueChange = onPasswordConfirmChange,
                placeholder = { Text("Ulangi kata sandi") },
                singleLine = true,
                isError = passwordConfirmError.isNotEmpty(),
                supportingText = {
                    Text(text = passwordConfirmError)
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = tandc,
                    onCheckedChange = onTandcChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White,
                    ),
                )

                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )

                Text(
                    text = "Saya setuju dengan syarat dan ketentuan yang berlaku",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }
            if (tandcError.isNotEmpty()) {
                Text(text = tandcError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = register,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // replace with your button color
                modifier = Modifier.fillMaxWidth(),
                enabled = enableRegisterButton
            ) {
                Text(text = "Daftar", color = Color.White)
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterPreview() {
    RegisterView(
        email = "",
        password = "",
        emailError = "",
        passwordError = "",
        nohp = "",
        tandc = false,
        onTandcChange = {},
        onNohpChange = {},
        nohpError = "",
        tandcError = "",
        onEmailChange = {},
        onPasswordChange = {},
        name = "",
        onNameChange = {},
        nameError = "",
        passwordConfirm = "",
        passwordConfirmError = "",
        onPasswordConfirmChange = {},
        navLogin = {},
        register = {},
        enableRegisterButton = true,
    )
}