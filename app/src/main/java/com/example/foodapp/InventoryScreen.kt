package com.example.foodapp


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun InventoryScreen() {
    var itemName by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }
    var expirationDate by remember { mutableStateOf(TextFieldValue("")) }
    val inventory = remember { mutableStateListOf<InventoryItem>() }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Add Item to Inventory", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(text = "Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text(text = "Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = expirationDate,
            onValueChange = { expirationDate = it },
            label = { Text(text = "Expiration Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (itemName.text.isNotEmpty() && quantity.text.isNotEmpty() && expirationDate.text.isNotEmpty()) {
                inventory.add(InventoryItem(itemName.text, quantity.text.toInt(), expirationDate.text))
                itemName = TextFieldValue("")
                quantity = TextFieldValue("")
                expirationDate = TextFieldValue("")
            }
        }) {
            Text(text = "Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(inventory) { item ->
                InventoryItemView(item)
            }
        }
    }
}

@Composable
fun InventoryItemView(item: InventoryItem) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Item: ${item.name}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Quantity: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Expires on: ${item.expirationDate}", style = MaterialTheme.typography.bodySmall)
    }
}

data class InventoryItem(val name: String, val quantity: Int, val expirationDate: String)

