package com.example.studyengine.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyengine.data.StudyRepository
import com.example.studyengine.model.Region
import com.example.studyengine.model.University
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityListScreen(
    region: Region,
    repository: StudyRepository,
    onUniversityClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val universities = repository.getUniversitiesByRegion(region)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(region.displayName + "院校排名") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(universities) { university ->
                UniversityItem(university, onUniversityClick)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun UniversityItem(university: University, onClick: (String) -> Unit) {
    ListItem(
        headlineContent = { Text(university.name) },
        supportingContent = { Text("QS排名: #${university.qsRank} | ${university.city}, ${university.country}") },
        leadingContent = {
            Image(
                painter = painterResource(id = university.logoResId),
                contentDescription = university.name,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
        },
        modifier = Modifier.clickable { onClick(university.id) }
    )
}
