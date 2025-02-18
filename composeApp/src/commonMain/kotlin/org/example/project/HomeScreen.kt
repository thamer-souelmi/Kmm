package org.example.project
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun HomeScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Rides", "Profile")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0A0F22), Color(0xFF1A2138))
                )
            )
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Hi Rider!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF00E5FF)
                )
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
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            RideStat("Battery", "85%", Color(0xFF76FF03))
                            RideStat("Range", "45 km", Color(0xFF00E5FF))
                            RideStat("Speed", "25 km/h", Color.White)
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
@Composable
fun GlassmorphicBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        NavigationItem("Home", Icons.Default.Home),
//        NavigationItem("Rides", Icons.Default.History),
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
                    selectedIconColor = Color(0xFF00E5FF),
                    selectedTextColor = Color(0xFF00E5FF),
                    unselectedIconColor = Color.White.copy(alpha = 0.8f),
                    unselectedTextColor = Color.White.copy(alpha = 0.8f),
                    indicatorColor = Color(0xFF00E5FF).copy(alpha = 0.1f)
                )
            )
        }
    }
}
@Composable
fun RideStat(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            title,
            color = Color.White.copy(alpha = 0.6f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.height(4.dp))
        Text(
            value,
            color = color,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

data class NavigationItem(val label: String, val icon: ImageVector)

