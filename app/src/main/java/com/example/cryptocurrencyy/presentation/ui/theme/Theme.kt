package com.example.cryptocurrency.presentation.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Define your custom color scheme for dark mode
private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimary,        // Replace Purple80 with your custom primary color
    secondary = DarkGray,          // Replace PurpleGrey80 with your custom secondary color
    tertiary = TextWhite,          // Replace Pink80 with your custom tertiary color
    background = DarkGray,         // Set custom background
    onBackground = TextWhite,      // Set custom text color on background
    onPrimary = MediumGray         // Set custom text color on primary
)

// Define your custom color scheme for light mode
private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,        // Replace Purple40 with your custom primary color
    secondary = MediumGray,        // Replace PurpleGrey40 with your custom secondary color
    tertiary = DarkGray,           // Replace Pink40 with your custom tertiary color
    background = Color.White,      // Set custom background
    onBackground = MediumGray,     // Set custom text color on background
    onPrimary = DarkGray           // Set custom text color on primary
)

@Composable
fun CryptoCurrencyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, // Apply your custom color schemes
        typography = Typography,   // Use your custom typography
        content = content
    )
}
