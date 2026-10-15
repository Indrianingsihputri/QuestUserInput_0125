package com.example.pertemuan5

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun FormDataDiri(modifier: Modifier) {
    //variabel2 untuk mengingat nilai masuk dari keyboard
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    val gender: List<String> = listOf("Laki-laki", "Perempuan")

    //variabel2 untuk menyimpan data yang diperoleh dari komponen UI
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Top),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = textNama,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(250.dp),
            label = { Text(text = "Nama Lengkap") },
            onValueChange = {
                textNama = it
            }
        )

        Row {
            gender.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = textJK == item,
                    onClick = { textJK = item }
                ), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = textJK == item,
                        onClick = {
                            textJK = item
                        }
                    )
                    Text(item)
                }
            }
        }
        OutlinedTextField(
            value = textAlamat,
            singleLine = true,
            modifier = Modifier.width(250.dp),
            label = { Text("Alamat") },
            onValueChange = {
                textAlamat = it
            }
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                bottom =
                    dimensionResource(id = R.dimen.padding_medium),
                    top = dimensionResource(
                        id = R.dimen.padding_medium)
            ),
            thickness = dimensionResource(id = R.dimen.divider_tipis),
            color = Color.DarkGray
        )
        Button(
            modifier = Modifier.fillMaxWidth(fraction = 1f),
            //
            enabled = textAlamat.isNotEmpty(),
            onClick = {
                nama = textNama
                jenis = textJK
                alamat = textAlamat
            }
        ) {
            Text(stringResource(id = R.string.submit))
        }
        HorizontalDivider(
            modifier = Modifier.padding(
                bottom =
                    dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(
                    id = R.dimen.padding_medium
                )
            )
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp)) {
                Text(text = "Nama : "+nama, color = Color.White)
                Text(text = "Jenis : "+jenis, color = Color.White)
                Text(text = "Alamat : "+alamat, color = Color.White)
            }
        }

    }
}