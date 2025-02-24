package org.example.project.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.login.GlassmorphicCard
import org.example.project.theme.AppTheme
import org.koin.compose.koinInject

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        var selectedItem by remember { mutableIntStateOf(0) }

        AppTheme(darkTheme = (state as HomeState.Default).darkTheme) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Hi Rider!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Row {
                            ThemeToggleButton(
                                darkTheme = (state as HomeState.Default).darkTheme,
                                onToggle = { viewModel.handleIntent(HomeIntent.ToggleTheme) }
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        GlassmorphicCard {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    "Current Ride",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                                )
                                Spacer(Modifier.height(8.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
//                                    RideStat("Battery", "85%", MaterialTheme.colorScheme.primary)
//                                    RideStat("Range", "45 km", MaterialTheme.colorScheme.secondary)
//                                    RideStat("Speed", "25 km/h", MaterialTheme.colorScheme.onBackground)
                                }
                            }
                        }
                    }
                }

                GlassmorphicBottomBar(
                    selectedIndex = selectedItem,
                    onItemSelected = { selectedItem = it },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        }
    }

    @Composable
    private fun ThemeToggleButton(darkTheme: Boolean, onToggle: () -> Unit) {
        IconButton(onClick = onToggle) {
            Icon(
                imageVector = if (darkTheme) Icons.Default.Phone else Icons.Default.MailOutline,
                contentDescription = "Toggle Theme",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    // Rest of the existing code (GlassmorphicBottomBar, RideStat, etc.) remains the same
    // Make sure to update color references to use MaterialTheme.colorScheme
}



@Composable
private fun GlassmorphicBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        NavigationItem("Home", Icons.Default.Home),
        NavigationItem("Profile", Icons.Default.Person)
    )

    NavigationBar(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(
                color = Color(0x1AFFFFFF),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ),
        containerColor = Color.Transparent
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

data class NavigationItem(
    val label: String,
    val icon: ImageVector
)