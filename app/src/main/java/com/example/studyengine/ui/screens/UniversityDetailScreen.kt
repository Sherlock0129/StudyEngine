package com.example.studyengine.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.studyengine.data.StudyRepository
import com.example.studyengine.model.Program
import com.example.studyengine.model.University

@Composable
fun UniversityDetailScreen(
    universityId: String,
    repository: StudyRepository,
    onEvaluateClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val university = repository.getUniversityById(universityId)
    val programs = repository.getProgramsByUniversity(universityId)

    if (university == null) {
        Text("University not found")
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 80.dp) // Leave space for bottom button
        ) {
            // 1. Cover Image & Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    // Cover Image
                    AsyncImage(
                        model = if (university.coverImageUrl.isNotEmpty()) university.coverImageUrl else university.logoResId,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Gradient Overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f)),
                                    startY = 100f
                                )
                            )
                    )

                    // Back Button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(top = 40.dp, start = 16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    // Play Video Button (Visual Only)
                    Icon(
                        Icons.Default.PlayCircle,
                        contentDescription = "Play Video",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )

                    // Floating Logo
                    Image(
                        painter = painterResource(id = university.logoResId),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 16.dp)
                            .offset(y = 30.dp) // Float over the edge
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(4.dp),
                        contentScale = ContentScale.Fit
                    )
                    
                    // Tags on Image (Bottom Left)
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        Surface(
                            color = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                "官方视频",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            // 2. University Info Section
            item {
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp) // Top padding for floating logo
                ) {
                    Text(
                        text = university.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = university.englishName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Country Flag (Text for now)
                        Surface(color = Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp)) {
                            Row(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
                                Text(university.country, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        // QS Rank
                        Surface(color = Color(0xFFFFF3E0), shape = RoundedCornerShape(4.dp)) {
                            Row(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("QS", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color(0xFFEF6C00))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("第${university.qsRank}名", style = MaterialTheme.typography.labelSmall, color = Color(0xFFEF6C00))
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Cost Info Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CostItem("学费", university.tuitionRange)
                        CostItem("住宿费", university.accommodationCost)
                        CostItem("生活费", university.livingCost)
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Description with Expand/Collapse
                    ExpandableText(text = university.description)
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Basic Info Table
                    InfoRow("中文名", university.name)
                    InfoRow("外文名", university.englishName)
                    InfoRow("简称", university.abbreviation)
                    InfoRow("学校官网", university.websiteUrl, isLink = true)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Tags List
                    if (university.tags.isNotEmpty()) {
                        Text("学校特色", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        university.tags.forEach { tag ->
                            Text(
                                text = "$tag 成员",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(thickness = 8.dp, color = Color(0xFFF5F5F5))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "开设硕士项目",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            items(programs) { program ->
                ProgramItem(program)
            }
        }
        
        // Bottom Button
        Button(
            onClick = onEvaluateClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("智能评估录取概率", fontSize = 16.sp)
        }
    }
}

@Composable
fun CostItem(label: String, value: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            if (label == "学费") {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.HelpOutline, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun ExpandableText(text: String) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(modifier = Modifier.animateContentSize()) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp
        )
        Text(
            text = if (expanded) "收起" else "展开",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(top = 4.dp)
        )
    }
}

@Composable
fun InfoRow(label: String, value: String, isLink: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.width(80.dp)
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            if (isLink) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("立即前往", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ProgramItem(program: Program) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White)
    ) {
        Text(program.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("学位: ${program.degree}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("最低GPA", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Text("${program.minGpa}", style = MaterialTheme.typography.bodyMedium)
            }
            Column {
                Text("学费", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Text(program.tuition, style = MaterialTheme.typography.bodyMedium)
            }
            Column {
                Text("语言要求", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Text("雅思${program.minIelts}", style = MaterialTheme.typography.bodyMedium)
            }
        }
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp), color = Color(0xFFEEEEEE))
    }
}
