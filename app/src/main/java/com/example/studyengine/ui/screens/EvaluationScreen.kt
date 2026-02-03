package com.example.studyengine.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyengine.data.StudyRepository
import com.example.studyengine.model.AdmissionTag
import com.example.studyengine.model.EvaluationResult
import com.example.studyengine.model.University
import com.example.studyengine.model.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluationScreen(
    universityId: String,
    repository: StudyRepository,
    onBackClick: () -> Unit
) {
    var gpa by remember { mutableStateOf("") }
    var ielts by remember { mutableStateOf("") }
    var toefl by remember { mutableStateOf("") }
    var hasResearch by remember { mutableStateOf(false) }
    var hasInternship by remember { mutableStateOf(false) }
    var targetMajor by remember { mutableStateOf("") }

    var results by remember { mutableStateOf<List<EvaluationResult>?>(null) }
    val university = repository.getUniversityById(universityId)

    if (university == null) {
        Text("University not found")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("智能选校评估") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        if (results == null) {
            // Input Form
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    UniversityHeader(university)
                }
                
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("背景信息", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = gpa,
                                onValueChange = { gpa = it },
                                label = { Text("GPA (4.0 scale)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                OutlinedTextField(
                                    value = ielts,
                                    onValueChange = { ielts = it },
                                    label = { Text("雅思总分") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                OutlinedTextField(
                                    value = toefl,
                                    onValueChange = { toefl = it },
                                    label = { Text("托福总分") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            OutlinedTextField(
                                value = targetMajor,
                                onValueChange = { targetMajor = it },
                                label = { Text("目标专业方向 (选填)") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = hasResearch, onCheckedChange = { hasResearch = it })
                                Text("拥有科研/论文发表经历")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = hasInternship, onCheckedChange = { hasInternship = it })
                                Text("拥有相关实习经历")
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = {
                            val profile = UserProfile(
                                gpa = gpa.toDoubleOrNull() ?: 0.0,
                                ieltsScore = ielts.toDoubleOrNull() ?: 0.0,
                                toeflScore = toefl.toIntOrNull() ?: 0,
                                hasResearchExp = hasResearch,
                                hasInternshipExp = hasInternship,
                                targetMajor = targetMajor
                            )
                            results = repository.evaluateProfile(profile, universityId)
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("生成评估报告", fontSize = 16.sp)
                    }
                }
            }
        } else {
            // Results Display
            EvaluationResultView(
                university = university,
                results = results!!,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun UniversityHeader(university: University) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = university.logoResId),
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(university.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(university.englishName, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Surface(color = Color(0xFFFFF3E0), shape = RoundedCornerShape(4.dp)) {
                    Text("QS第${university.qsRank}名", modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), color = Color(0xFFEF6C00), style = MaterialTheme.typography.labelSmall)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(color = Color(0xFFEEEEEE), shape = RoundedCornerShape(4.dp)) {
                    Text(university.country, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
fun EvaluationResultView(
    university: University,
    results: List<EvaluationResult>,
    modifier: Modifier = Modifier
) {
    var selectedTag by remember { mutableStateOf<AdmissionTag?>(null) } // null means All
    
    val reachCount = results.count { it.admissionTag == AdmissionTag.REACH }
    val matchCount = results.count { it.admissionTag == AdmissionTag.MATCH }
    val safetyCount = results.count { it.admissionTag == AdmissionTag.SAFETY }

    Row(modifier = modifier.fillMaxSize()) {
        // Sidebar
        Column(
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .background(Color.White)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SidebarTab(AdmissionTag.REACH, reachCount, selectedTag == AdmissionTag.REACH) { selectedTag = AdmissionTag.REACH }
            SidebarTab(AdmissionTag.MATCH, matchCount, selectedTag == AdmissionTag.MATCH) { selectedTag = AdmissionTag.MATCH }
            SidebarTab(AdmissionTag.SAFETY, safetyCount, selectedTag == AdmissionTag.SAFETY) { selectedTag = AdmissionTag.SAFETY }
        }

        // Content
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                UniversityHeader(university)
            }

            val filteredResults = if (selectedTag == null) results else results.filter { it.admissionTag == selectedTag }
            
            if (filteredResults.isEmpty()) {
                item {
                    Text("该分类下暂无推荐项目", style = MaterialTheme.typography.bodyMedium, color = Color.Gray, modifier = Modifier.padding(top = 32.dp))
                }
            }

            items(filteredResults) { result ->
                ResultCard(result)
            }
        }
    }
}

@Composable
fun SidebarTab(tag: AdmissionTag, count: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            color = if (isSelected) Color(tag.colorHex) else Color.LightGray,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(50.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = tag.displayName.take(2),
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = count.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ResultCard(result: EvaluationResult) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = result.university.logoResId),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clip(RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(result.university.name, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                
                Surface(
                    color = Color(result.admissionTag.colorHex),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("<${if (result.probability > 90) 90 else result.probability}%", color = Color.White, style = MaterialTheme.typography.labelSmall)
                        Text(result.admissionTag.displayName.take(2), color = Color.White, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(result.program.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Tags
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                result.program.categoryTags.take(3).forEach { tag ->
                    Surface(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(4.dp)) {
                        Text(tag, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(36.dp),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("查看申请要求 >", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
