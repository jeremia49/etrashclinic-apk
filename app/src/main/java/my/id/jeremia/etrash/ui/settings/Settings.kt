package my.id.jeremia.etrash.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.header.HeaderSection


@Composable
fun SettingsView(modifier: Modifier = Modifier, viewModel: SettingsViewModel) {
    BackgroundImage {
        SettingsScreen(
            modifier = modifier,
            onLogout = { viewModel.logout() }
        )
    }
}


@Composable
fun SettingsScreen(modifier: Modifier = Modifier, onLogout: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        HeaderSection("", "", isRoot = false)
        Spacer(modifier = Modifier.height(16.dp))
        SettingContent()
        Spacer(modifier = Modifier.weight(1f))
        LogoutButton(onLogout)
    }
}

@Composable
fun SettingContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pengaturan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Akun Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        SettingItem("Ubah profil")
        SettingItem("Ubah kata sandi")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                Icons.Default.Info,
                contentDescription = "About Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Tentang",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        SettingItem("Panduan")
        SettingItem("Syarat dan ketentuan")
        SettingItem("Kebijakan Privasi")
        SettingItem("Pertanyaan Umum")
    }
}

@Composable
fun SettingCategory(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = "$title Icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun SettingItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black
        )
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Arrow",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun LogoutButton(onLogout: () -> Unit) {
    Button(
        onClick = onLogout,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
    ) {
        Text(text = "Keluar", color = Color.White, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}