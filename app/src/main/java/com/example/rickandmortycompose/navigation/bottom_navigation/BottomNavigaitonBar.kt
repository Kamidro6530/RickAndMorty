package com.example.rickandmortycompose

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickandmortycompose.navigation.bottom_navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(BottomNavigationItem.ListCharacters, BottomNavigationItem.ListEpisodes)

    BottomNavigation(backgroundColor = MaterialTheme.colors.primaryVariant, elevation = 5.dp) {
        items.forEach { item ->

            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate(item.routes) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name
                    )
                }
            )

        }
    }
}