package com.mahim.mkcam.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mahim.mkcam.ui.components.GlassButton
import com.mahim.mkcam.ui.theme.BackgroundDark
import com.mahim.mkcam.ui.theme.TextWhite

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var apiKey by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(16.dp)
    ) {
        GlassButton(
            text = "Back",
            icon = Icons.Default.ArrowBack,
            onClick = onNavigateBack
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Settings & API Keys",
            style = MaterialTheme.typography.titleLarge,
            color = TextWhite
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Add your API key to enable optional cloud-based, higher-quality image enhancements. Keys are stored securely.",
            color = TextWhite.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = apiKey,
            onValueChange = { apiKey = it },
            label = { Text("Gemini / Cloud API Key") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        GlassButton(
            text = "Save Key",
            onClick = {
                Toast.makeText(context, "Key Saved (Mock)", Toast.LENGTH_SHORT).show()
                apiKey = ""
            },
            modifier = Modifier.fillMaxWidth()
        )

         Spacer(modifier = Modifier.height(16.dp))
        
         GlassButton(
            text = "Clear All Keys",
            onClick = {
                 Toast.makeText(context, "Keys Cleared (Mock)", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
