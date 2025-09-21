package com.example.bluecrew.ui.add_projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bluecrew.R
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddProjectScreen(navController: NavController) {
    var projectTitle by remember { mutableStateOf("") }
    var selectedPlantationType by remember { mutableStateOf<String?>(null) }
    var areaOfPlantation by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Add Project",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // New/Existing Plantation Options
            Text(
                text = "Is this a new or existing plantation?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ChoiceChip(
                    text = "New Plantation",
                    isSelected = selectedPlantationType == "new",
                    onClick = { selectedPlantationType = "new" },
                    modifier = Modifier.weight(1f)
                )
                ChoiceChip(
                    text = "Existing Plantation",
                    isSelected = selectedPlantationType == "existing",
                    onClick = { selectedPlantationType = "existing" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


                // Project Title
                TextFieldWithLabel(
                    label = "Project Title",
                    placeholder = "TITLE",
                    value = projectTitle,
                    onValueChange = { projectTitle = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Type of plant (Dropdown)
                TextFieldWithLabel(
                    label = "Type of plant",
                    placeholder = "Type of plant",
                    value = "", // Placeholder for dropdown
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Area of Plantation
                TextFieldWithLabel(
                    label = "Area of Plantation",
                    placeholder = "in hectares",
                    value = areaOfPlantation,
                    onValueChange = { areaOfPlantation = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Project Description
                TextFieldWithLabel(
                    label = "Project Description",
                    placeholder = "Provide a brief description of the project goals and methods.",
                    value = projectDescription,
                    onValueChange = { projectDescription = it },
                    singleLine = false,
                    modifier = Modifier.height(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Geo-tagged Photos
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Geo-tagged Photos",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .border(1.5.dp, Color.LightGray, RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp))
                            .padding(16.dp)
                            .clickable { /* TODO: Handle image upload */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Upload",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder
                    ) {
                        Text("Cancel", color = Color.Gray)
                    }
                    Button(
                        onClick = { /* TODO: Submit project */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Submit Project", color = Color.White)
                    }
                }

        }
    }
}

@Composable
fun ChoiceChip(text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
    val contentColor = if (isSelected) Color.White else Color.Gray

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            placeholder = { Text(placeholder) },
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddProjectScreen() {
    AddProjectScreen(navController = rememberNavController())
}
