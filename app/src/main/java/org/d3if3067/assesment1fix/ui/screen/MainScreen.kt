package org.d3if3067.assesment1fix.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3067.assesment1fix.R
import org.d3if3067.assesment1fix.ui.theme.Assesment1FIxTheme
import org.d3if3067.assesment1fix.navigation.Screen
import org.d3if3067.assesment1fix.ui.theme.Assesment1FIxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_developer),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Menu.route)
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu_catatan),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        content = { padding ->
            ScreenContent(Modifier.padding(padding))
        }
    )
}


@Composable
fun ScreenContent(modifier: Modifier) {
    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    var hari by rememberSaveable { mutableStateOf("") }
    var hariError by rememberSaveable { mutableStateOf(false) }

    var harga by rememberSaveable { mutableFloatStateOf(0f) }
    var berapa_hari by rememberSaveable { mutableIntStateOf(0) }

    // State for radio button
    var applyDiscount by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text(text = stringResource(R.string.berat)) },
            isError = beratError,
            trailingIcon = { IconPicker(beratError, "Kg") },
            supportingText = { ErrorHint(beratError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = hari,
            onValueChange = { hari = it },
            label = { Text(text = stringResource(R.string.hari)) },
            isError = hariError,
            trailingIcon = { IconPicker(hariError, "Hari") },
            supportingText = { ErrorHint(hariError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    // Reset values
                    berat = ""
                    beratError = false
                    hari = ""
                    hariError = false
                    harga = 0f
                    berapa_hari = 0
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.reset))
            }

            // Hitung Button
            Button(
                onClick = {
                    beratError = (berat == "" || berat == "0")
                    hariError = (hari == "" || hari == "0")
                    if (beratError || hariError) return@Button

                    harga = hitungHargaLaundry(berat.toFloat(), hari.toInt(),applyDiscount)
                    berapa_hari = hari.toInt()
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
        }
        // Radio button untuk menerapkan diskon
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = applyDiscount,
                onClick = { applyDiscount = !applyDiscount },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Terapkan Diskon Rp.2000")
        }

        // Tombol Bagikan
        if (harga != 0f && berapa_hari != 0) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.harga, harga),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.berapa_hari, berapa_hari),
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template, berat, hari, harga)
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }
}


@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun hitungHargaLaundry(berat: Float, hari: Int, applyDiscount: Boolean = false): Float {
    val basePricePerKg = 6000 // Set the base price per Kg (you can adjust this value)
    val discountPerDay = 1000 // Discount applied for each day (you can adjust this value)
    val priceIncreasePerDay = 1000 // Price increase for each day (you can adjust this value)

    val totalPrice = berat * basePricePerKg

    // Apply discount if the applyDiscount flag is true
    val totalDiscount = if (applyDiscount) {
        discountPerDay * hari
    } else {
        0
    }

    // Increase price if the number of days decreases by 1
    val totalIncrease = priceIncreasePerDay * (hari - 1)

    return totalPrice - totalDiscount + totalIncrease
}



private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}
