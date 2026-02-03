package com.example.studyengine.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studyengine.data.StudyRepository
import com.example.studyengine.ui.screens.*
import com.example.studyengine.model.Region

@Composable
fun StudyEngineApp() {
    val navController = rememberNavController()
    val repository = StudyRepository()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onRegionSelected = { region ->
                    navController.navigate("all_universities?region=${region.name}")
                },
                onCheckSchoolsClicked = {
                    navController.navigate("all_universities")
                }
            )
        }

        composable(
            "all_universities?region={region}",
            arguments = listOf(navArgument("region") { 
                type = NavType.StringType 
                nullable = true
                defaultValue = null
            })
        ) { backStackEntry ->
            val regionName = backStackEntry.arguments?.getString("region")
            val initialRegion = regionName?.let { Region.valueOf(it) }
            
            AllUniversitiesScreen(
                repository = repository,
                initialRegion = initialRegion,
                onUniversityClick = { universityId ->
                    navController.navigate("detail/$universityId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            "list/{regionName}",
            arguments = listOf(navArgument("regionName") { type = NavType.StringType })
        ) { backStackEntry ->
            val regionName = backStackEntry.arguments?.getString("regionName")
            val region = Region.valueOf(regionName ?: Region.HK_SG.name)
            UniversityListScreen(
                region = region,
                repository = repository,
                onUniversityClick = { universityId ->
                    navController.navigate("detail/$universityId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            "detail/{universityId}",
            arguments = listOf(navArgument("universityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getString("universityId") ?: ""
            UniversityDetailScreen(
                universityId = universityId,
                repository = repository,
                onEvaluateClick = {
                    navController.navigate("evaluate/$universityId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            "evaluate/{universityId}",
            arguments = listOf(navArgument("universityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getString("universityId") ?: ""
            EvaluationScreen(
                universityId = universityId,
                repository = repository,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
