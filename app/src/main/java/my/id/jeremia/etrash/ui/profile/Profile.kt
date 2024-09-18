package my.id.jeremia.etrash.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.dhaval2404.imagepicker.ImagePicker
import my.id.jeremia.etrash.BuildConfig
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.settings.LogoutButton
import my.id.jeremia.etrash.ui.settings.SettingsScreen
import my.id.jeremia.etrash.ui.settings.SettingsViewModel
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.utils.common.saveImageToStorage


@Composable
fun ProfileView(modifier: Modifier = Modifier, viewModel: ProfileViewModel) {
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!
                //updatePhotoUrl
                viewModel.selectedImage = fileUri
                viewModel.uploadImage(
                    saveImageToStorage(context, viewModel.selectedImage!!)!!
                )
            }
        }
    )


    BackgroundImage(showImage = true) {
        ProfileScreen(
            modifier = modifier,
            name = viewModel.namapengguna.collectAsStateWithLifecycle().value,
            photoUrl = viewModel.photoUrl.collectAsStateWithLifecycle().value,
            nohp = viewModel.nohp.collectAsStateWithLifecycle().value,
            onSave = {
                viewModel.saveProfile()
            },
            onClickImage = {
                ImagePicker.with(context as AppCompatActivity)
                    .galleryOnly()
                    .compress(4096)
                    .maxResultSize(1080, 1080)
                    .createIntent { intent ->
                        imagePickerLauncher.launch(intent)
                    }
            },
            onNameChange = viewModel::onNameChange,
            onNohpChange = viewModel::onNohpChange,
        )
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    photoUrl: String = "",
    name: String = "",
    nohp: String = "",
    onSave: () -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onNohpChange: (String) -> Unit = {},
    onClickImage: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        ProfileContent(
            photoUrl,
            name,
            nohp,
            onSave,
            onNameChange,
            onNohpChange,
            onClickImage
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    photoUrl: String = "",
    name: String = "",
    nohp: String = "",
    onSave: () -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onNohpChange: (String) -> Unit = {},
    onClickImage: () -> Unit = {}
) {

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top,

        ) {

        Text(
            text = "Profile",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier) {
                NetworkImage(
                    url = photoUrl,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, RoundedCornerShape(50))
                        .clickable {
                            onClickImage()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.editprofilepicture),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
            }

        }

        Text(
            text = "Nama",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp, bottom = 0.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text("Nama") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                errorContainerColor = Color.White,
            ),
        )


        Text(
            text = "Nomor HP",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp, bottom = 0.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = nohp,
            onValueChange = onNohpChange,
            placeholder = { Text("Nomor HP") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                errorContainerColor = Color.White,
            ),
        )


        Button(
            onClick = onSave, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            Text("Simpan perubahan")
        }
    }


}