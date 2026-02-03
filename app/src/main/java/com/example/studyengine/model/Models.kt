package com.example.studyengine.model

import androidx.annotation.DrawableRes
import com.example.studyengine.R

enum class Region(val displayName: String, @DrawableRes val imageRes: Int) {
    HK_SG("中国香港 & 新加坡", R.drawable.region_hksg),
    EUROPE("欧洲", R.drawable.region_europe),
    NORTH_AMERICA("北美", R.drawable.region_na),
    AUSTRALIA("澳洲", R.drawable.region_aus)
}

enum class AdmissionTag(val displayName: String, val colorHex: Long) {
    REACH("冲刺 (Reach)", 0xFFE57373), // Red
    MATCH("匹配 (Match)", 0xFF64B5F6), // Blue
    SAFETY("保底 (Safety)", 0xFF81C784)  // Green
}

data class University(
    val id: String,
    val name: String,
    val englishName: String = "",
    val abbreviation: String = "",
    val qsRank: Int,
    val country: String,
    val city: String,
    val region: Region,
    val description: String = "",
    val websiteUrl: String = "",
    @DrawableRes val logoResId: Int, // Changed from String URL to Int Resource ID
    val coverImageUrl: String = "",
    val tags: List<String> = emptyList(),
    val interestCount: String = "0",
    val tuitionRange: String = "",
    val accommodationCost: String = "",
    val livingCost: String = ""
)

data class Program(
    val id: String,
    val universityId: String,
    val name: String,
    val degree: String,
    val minGpa: Double, // 4.0 scale
    val minIelts: Double,
    val minToefl: Int,
    val deadline: String,
    val tuition: String,
    val categoryTags: List<String> = emptyList() // e.g. ["理科", "数据科学", "工程学院"]
)

data class UserProfile(
    val gpa: Double,
    val ieltsScore: Double,
    val toeflScore: Int,
    val hasResearchExp: Boolean,
    val hasInternshipExp: Boolean,
    val targetMajor: String
)

data class EvaluationResult(
    val program: Program,
    val university: University,
    val admissionTag: AdmissionTag,
    val probability: Int, // 0-100%
    val suggestions: List<String>
)
