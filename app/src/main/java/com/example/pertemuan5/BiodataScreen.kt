package com.example.pertemuan5

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Patterns
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Locale

@SuppressLint("LocalContextGetResourceValueCall")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataScreen() {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiUxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    val experienceOptions = listOf(
        "Beginner",
        "Intermediate",
        "Advanced"
    )

    val calendar = Calendar.getInstance()

    Scaffold(
        containerColor = colorResource(R.color.background)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header image
            Image(
                painter = painterResource(R.drawable.langit),
                contentDescription = stringResource(R.string.header_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop
            )

            // Biodata card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-12).dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.form_title),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.purple_primary)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.form_description),
                    fontSize = 11.sp,
                    color = colorResource(R.color.text_secondary),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(22.dp))

                // First name and last name
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        modifier = Modifier.weight(1f),
                        label = {
                            Text(stringResource(R.string.first_name))
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        modifier = Modifier.weight(1f),
                        label = {
                            Text(stringResource(R.string.last_name))
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(stringResource(R.string.email))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Birth date and experience
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            DatePickerDialog(
                                context,
                                { _: DatePicker, year: Int,
                                  month: Int, day: Int ->
                                    birthdate = String.format(
                                        Locale.US,
                                        "%02d/%02d/%04d",
                                        day, month + 1, year
                                    )
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(
                            horizontal = 8.dp,
                            vertical = 14.dp
                        )
                    ) {
                        Text(
                            text = birthdate.ifEmpty {
                                stringResource(R.string.birth_date)
                            },
                            maxLines = 1
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { expanded = true },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(
                                horizontal = 8.dp,
                                vertical = 14.dp
                            )
                        ) {
                            Text(
                                text = experience.ifEmpty {
                                    stringResource(R.string.experience)
                                },
                                maxLines = 1
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            experienceOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        experience = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Skills title
                Text(
                    text = stringResource(R.string.skills_title),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = colorResource(R.color.text_primary)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Skills checkboxes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SkillCheckbox(
                        label = stringResource(R.string.skill_android),
                        checked = androidSkill,
                        onCheckedChange = { androidSkill = it }
                    )

                    SkillCheckbox(
                        label = stringResource(R.string.skill_uiux),
                        checked = uiUxSkill,
                        onCheckedChange = { uiUxSkill = it }
                    )

                    SkillCheckbox(
                        label = stringResource(R.string.skill_web),
                        checked = webSkill,
                        onCheckedChange = { webSkill = it }
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Proceed button
                Button(
                    onClick = {
                        message = when {
                            firstName.isBlank() ||
                                    lastName.isBlank() ||
                                    email.isBlank() ||
                                    birthdate.isBlank() ||
                                    experience.isBlank() -> {
                                context.getString(R.string.error_required)
                            }

                            !Patterns.EMAIL_ADDRESS
                                .matcher(email).matches() -> {
                                context.getString(R.string.error_email)
                            }

                            !androidSkill &&
                                    !uiUxSkill &&
                                    !webSkill -> {
                                context.getString(R.string.error_skill)
                            }

                            else -> {
                                context.getString(
                                    R.string.success_message,
                                    firstName,
                                    lastName
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            colorResource(R.color.purple_primary)
                    )
                ) {
                    Text(stringResource(R.string.proceed))
                }

                if (message.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = message,
                        color = colorResource(R.color.purple_primary),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = label,
            fontSize = 11.sp
        )
    }
}
