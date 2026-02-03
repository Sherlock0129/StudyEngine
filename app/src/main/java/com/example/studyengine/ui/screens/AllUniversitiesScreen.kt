package com.example.studyengine.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.studyengine.data.StudyRepository
import com.example.studyengine.model.Region
import com.example.studyengine.model.University
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllUniversitiesScreen(
    repository: StudyRepository,
    initialRegion: Region? = null,
    onUniversityClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("学校", "专业", "案例")
    
    var selectedRegion by remember { mutableStateOf(initialRegion) }
    var showFilterSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    val universities = remember(selectedRegion) {
        if (selectedRegion != null) {
            repository.getUniversitiesByRegion(selectedRegion!!)
        } else {
            repository.getAllUniversities()
        }
    }

    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = bottomSheetState
        ) {
            FilterBottomSheetContent(
                currentRegion = selectedRegion,
                onRegionSelected = { region ->
                    selectedRegion = region
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        showFilterSheet = false
                    }
                },
                onReset = {
                    selectedRegion = null
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        showFilterSheet = false
                    }
                }
            )
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .statusBarsPadding()
            ) {
                // Top Navigation Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable { selectedTab = index }
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedTab == index) Color.Black else Color.Gray
                                )
                                if (selectedTab == index) {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 4.dp)
                                            .width(20.dp)
                                            .height(3.dp)
                                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.height(7.dp))
                                }
                            }
                        }
                    }
                    
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Light Gray Background
        ) {
            // Ranking Cards Section
            item {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { RankingCard("世界大学", "2026 QS", "综合排名", Color(0xFFFFB74D)) }
                    item { RankingCard("英国大学", "2025 Times", "综合排名", Color(0xFF7E57C2)) }
                    item { RankingCard("美国大学", "2026 U.S. News", "综合排名", Color(0xFF42A5F5)) }
                }
            }

            // Filter Dropdowns
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    FilterDropdown(
                        text = selectedRegion?.displayName?.let { "地区: $it" } ?: "地区/排名",
                        onClick = { showFilterSheet = true },
                        isSelected = selectedRegion != null
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    FilterDropdown("智能排序", onClick = {})
                }
            }

            // University List
            items(universities) { university ->
                DetailedUniversityItem(university, onUniversityClick)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheetContent(
    currentRegion: Region?,
    onRegionSelected: (Region) -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("地区", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        
        // Region Grid
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = currentRegion == null,
                onClick = onReset,
                label = { Text("不限") }
            )
            
            Region.values().forEach { region ->
                FilterChip(
                    selected = currentRegion == region,
                    onClick = { onRegionSelected(region) },
                    label = { Text(region.displayName) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Placeholder for Ranking (as per image)
        Text("学校排名", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
             FilterChip(selected = true, onClick = {}, label = { Text("QS 世界大学排名") })
             FilterChip(selected = false, onClick = {}, label = { Text("Times 英国大学排名") })
             FilterChip(selected = false, onClick = {}, label = { Text("U.S.News 美国大学排名") })
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RankingCard(title: String, subtitle: String, desc: String, color: Color) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
            Column {
                Text(text = subtitle, color = Color.White.copy(alpha = 0.9f), style = MaterialTheme.typography.bodySmall)
                Text(text = desc, color = Color.White.copy(alpha = 0.9f), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun FilterDropdown(text: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = text, 
            style = MaterialTheme.typography.bodyMedium, 
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.DarkGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
        Icon(
            Icons.Default.ArrowDropDown, 
            contentDescription = null, 
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}

@Composable
fun DetailedUniversityItem(university: University, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(university.id) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Logo
            Image(
                painter = painterResource(id = university.logoResId),
                contentDescription = university.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = university.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = university.englishName,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            maxLines = 1
                        )
                    }
                    
                    // QS Rank Badge
                    Surface(
                        color = Color(0xFFFFF3E0),
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 4.dp, bottomEnd = 4.dp, bottomStart = 4.dp),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${university.qsRank}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFEF6C00)
                            )
                            Text(
                                text = "QS排名",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFEF6C00),
                                fontSize = 8.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Tags
                Row(modifier = Modifier.fillMaxWidth()) {
                    university.tags.take(3).forEach { tag ->
                        Surface(
                            color = Color(0xFFE3F2FD),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.padding(end = 6.dp)
                        ) {
                            Text(
                                text = tag,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF1976D2)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Location
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Assuming we could parse region to flag/icon later
                    Text(
                        text = "${university.country} / ${university.city}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Interest Count
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocalFireDepartment,
                        contentDescription = "Hot",
                        tint = Color(0xFFFF7043),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${university.interestCount} 人感兴趣",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFF7043)
                    )
                }
            }
        }
    }
}
