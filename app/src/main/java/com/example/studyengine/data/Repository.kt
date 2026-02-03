package com.example.studyengine.data

import com.example.studyengine.model.*

import com.example.studyengine.R

class StudyRepository {

    private val universities = listOf(
        // HK & SG
        University(
            id = "u1",
            name = "新加坡国立大学 (NUS)",
            englishName = "National University of Singapore",
            abbreviation = "NUS",
            qsRank = 8,
            country = "新加坡",
            city = "新加坡",
            region = Region.HK_SG,
            description = "新加坡国立大学（National University of Singapore），简称国大（NUS），是新加坡的首屈一指的世界级顶尖大学。该校是环太平洋大学联盟、亚洲大学联盟、亚太国际教育协会、国际研究型大学联盟、Universitas 21等著名高校联盟的成员，也通过AACSB和EQUIS认证。",
            websiteUrl = "http://www.nus.edu.sg",
            logoResId = R.drawable.logo_nus,
            coverImageUrl = "https://www.nus.edu.sg/images/default-source/base/logo.png",
            tags = listOf("亚洲大学联盟", "环太平洋大学联盟", "亚太国际教育协会"),
            interestCount = "14w",
            tuitionRange = "2.5-9.4万新币",
            accommodationCost = "约9万人民币",
            livingCost = "约10.6万人民币"
        ),
        University(
            id = "u2",
            name = "南洋理工大学 (NTU)",
            englishName = "Nanyang Technological University",
            abbreviation = "NTU",
            qsRank = 15,
            country = "新加坡",
            city = "新加坡",
            region = Region.HK_SG,
            description = "南洋理工大学（Nanyang Technological University），简称南大（NTU），是新加坡的一所世界著名研究型大学。南大是环太平洋大学联盟成员，全球高校人工智能学术联盟创始成员、AACSB认证成员、国际事务专业学院协会（APSIA）成员，也是国际科技大学联盟的发起成员。",
            websiteUrl = "https://ntu.edu.sg",
            logoResId = R.drawable.logo_ntu,
            coverImageUrl = "https://www.ntu.edu.sg/images/default-source/default-album/ntu-campus.jpg",
            tags = listOf("工科强校", "AACSB认证", "AI强校"),
            interestCount = "12w",
            tuitionRange = "2.8-8.5万新币",
            accommodationCost = "约8.5万人民币",
            livingCost = "约10万人民币"
        ),
        University(
            id = "u3",
            name = "香港大学 (HKU)",
            englishName = "The University of Hong Kong",
            abbreviation = "HKU",
            qsRank = 17,
            country = "中国香港",
            city = "香港",
            region = Region.HK_SG,
            description = "香港大学（The University of Hong Kong），简称“港大”（HKU），是中国香港的一所国际化公立研究型大学，有亚洲“常春藤”之称。校训为“明德格物”，对应拉丁文为Sapientia et Virtus。",
            websiteUrl = "https://hku.hk",
            logoResId = R.drawable.logo_hku,
            coverImageUrl = "https://www.hku.hk/f/page/10543/10543_1.jpg",
            tags = listOf("亚洲“常春藤”", "中国大学校长联谊会", "Universitas 21"),
            interestCount = "19.7w",
            tuitionRange = "18-40万港币",
            accommodationCost = "约8万人民币",
            livingCost = "约10万人民币"
        ),
        University(
            id = "u4",
            name = "香港中文大学 (CUHK)",
            englishName = "The Chinese University of Hong Kong",
            abbreviation = "CUHK",
            qsRank = 36,
            country = "中国香港",
            city = "香港",
            region = Region.HK_SG,
            description = "香港中文大学（The Chinese University of Hong Kong），简称港中大（CUHK），是一所亚洲顶尖、享誉国际的公立研究型综合大学，在人文学科、数学、计算机科学、经济与金融、医学、法律、传媒、地理等领域堪称世界级学术重镇。",
            websiteUrl = "https://cuhk.edu.hk",
            logoResId = R.drawable.logo_cuhk,
            coverImageUrl = "https://www.cuhk.edu.hk/chinese/images/cuhk_campus.jpg",
            tags = listOf("书院制", "研究型大学", "AACSB认证"),
            interestCount = "8w",
            tuitionRange = "16-35万港币",
            accommodationCost = "约7万人民币",
            livingCost = "约9万人民币"
        ),
        
        // Europe
        University(
            id = "u5",
            name = "帝国理工学院 (Imperial)",
            englishName = "Imperial College London",
            abbreviation = "Imperial",
            qsRank = 2,
            country = "英国",
            city = "伦敦",
            region = Region.EUROPE,
            description = "帝国理工学院（Imperial College London），是一所主攻理学、工学、医学和商学的世界顶尖公立研究型大学。全称为帝国科学、技术与医学学院（Imperial College of Science, Technology and Medicine）。",
            websiteUrl = "https://imperial.ac.uk",
            logoResId = R.drawable.logo_imperial,
            coverImageUrl = "https://www.imperial.ac.uk/ImageCropToolT4/imageTool/uploaded-images/new-portico--to-jpeg_1533290685975_x1.jpg",
            tags = listOf("G5超级精英大学", "金三角名校", "罗素集团"),
            interestCount = "10w",
            tuitionRange = "3.5-4.5万英镑",
            accommodationCost = "约12万人民币",
            livingCost = "约15万人民币"
        ),
        University(
            id = "u6",
            name = "牛津大学 (Oxford)",
            englishName = "University of Oxford",
            abbreviation = "Oxford",
            qsRank = 3,
            country = "英国",
            city = "牛津",
            region = Region.EUROPE,
            description = "牛津大学（University of Oxford），简称“牛津”（Oxford），位于英国牛津，是一所公立研究型大学，采用书院联邦制。其与剑桥大学并称为“牛剑”，是罗素大学集团成员，被誉为“金三角名校”和“G5超级精英大学”。",
            websiteUrl = "https://ox.ac.uk",
            logoResId = R.drawable.logo_oxford,
            coverImageUrl = "https://www.ox.ac.uk/sites/files/oxford/styles/ow_medium_feature/public/field/field_image_main/Radcliffe_Camera.jpg",
            tags = listOf("G5超级精英大学", "罗素大学集团", "书院联邦制"),
            interestCount = "15w",
            tuitionRange = "3.0-4.8万英镑",
            accommodationCost = "约10万人民币",
            livingCost = "约12万人民币"
        ),
        University(
            id = "u7",
            name = "苏黎世联邦理工学院 (ETH)",
            englishName = "ETH Zurich",
            abbreviation = "ETH",
            qsRank = 7,
            country = "瑞士",
            city = "苏黎世",
            region = Region.EUROPE,
            description = "苏黎世联邦理工学院（ETH Zurich），由瑞士联邦政府创建于1854年，与姊妹校洛桑联邦理工学院一起组成瑞士联邦理工学院，是瑞士联邦经济事务、教育与研究部的一部分。爱因斯坦的母校。",
            websiteUrl = "https://ethz.ch",
            logoResId = R.drawable.logo_eth,
            coverImageUrl = "https://ethz.ch/content/dam/ethz/special-interest/baug/ibk/structural-mechanics-dam/images/ETH_Hauptgebaeude.jpg",
            tags = listOf("欧陆第一名校", "IDEA联盟", "爱因斯坦母校"),
            interestCount = "6w",
            tuitionRange = "约1500瑞士法郎/年",
            accommodationCost = "约10万人民币",
            livingCost = "约15万人民币"
        ),
        
        // North America
        University(
            id = "u8",
            name = "麻省理工学院 (MIT)",
            englishName = "Massachusetts Institute of Technology",
            abbreviation = "MIT",
            qsRank = 1,
            country = "美国",
            city = "剑桥",
            region = Region.NORTH_AMERICA,
            description = "麻省理工学院（Massachusetts Institute of Technology），简称“麻省理工”（MIT），位于美国马萨诸塞州波士顿都市区剑桥市，主校区依查尔斯河而建，是世界著名私立研究型大学。",
            websiteUrl = "https://mit.edu",
            logoResId = R.drawable.logo_mit,
            coverImageUrl = "https://news.mit.edu/sites/default/files/styles/news_article__image_gallery/public/images/202010/MIT-Dome-Night-01.jpg",
            tags = listOf("常春藤", "AAU成员", "理工天花板"),
            interestCount = "20w",
            tuitionRange = "5.5-6.0万美元",
            accommodationCost = "约12万人民币",
            livingCost = "约15万人民币"
        ),
        University(
            id = "u9",
            name = "斯坦福大学 (Stanford)",
            englishName = "Stanford University",
            abbreviation = "Stanford",
            qsRank = 5,
            country = "美国",
            city = "斯坦福",
            region = Region.NORTH_AMERICA,
            description = "斯坦福大学（Stanford University），全名小利兰·斯坦福大学（Leland Stanford Junior University），简称“斯坦福”，位于美国加州旧金山湾区南部帕罗奥多市境内，临近高科技园区硅谷（Silicon Valley），是私立研究型大学。",
            websiteUrl = "https://stanford.edu",
            logoResId = R.drawable.logo_stanford,
            coverImageUrl = "https://www.stanford.edu/wp-content/uploads/2017/03/memorial-church-1.jpg",
            tags = listOf("私立研究型大学", "硅谷核心", "AAU成员"),
            interestCount = "18w",
            tuitionRange = "5.8-6.5万美元",
            accommodationCost = "约13万人民币",
            livingCost = "约16万人民币"
        ),
        University(
            id = "u10",
            name = "多伦多大学 (UofT)",
            englishName = "University of Toronto",
            abbreviation = "UofT",
            qsRank = 21,
            country = "加拿大",
            city = "多伦多",
            region = Region.NORTH_AMERICA,
            description = "多伦多大学（University of Toronto），简称多大（UofT），位于加拿大安大略省多伦多，是一所公立联邦制研究型大学，美国大学协会成员。多伦多大学始建于1827年。",
            websiteUrl = "https://utoronto.ca",
            logoResId = R.drawable.logo_uoft,
            coverImageUrl = "https://www.utoronto.ca/sites/default/files/uc-quad.jpg",
            tags = listOf("公立研究型大学", "U15", "AAU成员"),
            interestCount = "9w",
            tuitionRange = "4.0-6.5万加币",
            accommodationCost = "约8万人民币",
            livingCost = "约10万人民币"
        ),

        // Australia
        University(
            id = "u11",
            name = "墨尔本大学 (UniMelb)",
            englishName = "The University of Melbourne",
            abbreviation = "UniMelb",
            qsRank = 14,
            country = "澳洲",
            city = "墨尔本",
            region = Region.AUSTRALIA,
            description = "墨尔本大学（The University of Melbourne），1853年始建于澳大利亚墨尔本，是一所公立研究型大学，是环太平洋大学联盟、亚太国际教育协会、国际研究型大学联盟成员。",
            websiteUrl = "https://unimelb.edu.au",
            logoResId = R.drawable.logo_unimelb,
            coverImageUrl = "https://www.unimelb.edu.au/__data/assets/image/0009/1932786/Old-Arts-Clocktower.jpg",
            tags = listOf("澳洲八校联盟", "环太平洋大学联盟", "砂岩学府"),
            interestCount = "11w",
            tuitionRange = "4.5-5.5万澳币",
            accommodationCost = "约9万人民币",
            livingCost = "约11万人民币"
        ),
        University(
            id = "u12",
            name = "悉尼大学 (USYD)",
            englishName = "The University of Sydney",
            abbreviation = "USYD",
            qsRank = 19,
            country = "澳洲",
            city = "悉尼",
            region = Region.AUSTRALIA,
            description = "悉尼大学（The University of Sydney），简称“悉大”，坐落于澳大利亚悉尼，是一所公立研究型大学，是砂岩学府、环太平洋大学联盟、澳大利亚八校联盟、亚太国际教育协会、世界大学联盟成员。",
            websiteUrl = "https://sydney.edu.au",
            logoResId = R.drawable.logo_usyd,
            coverImageUrl = "https://www.sydney.edu.au/content/dam/corporate/images/campus/quadrangle/quadrangle-1.jpg",
            tags = listOf("澳洲八校联盟", "砂岩学府", "AACSB认证"),
            interestCount = "10w",
            tuitionRange = "4.8-5.8万澳币",
            accommodationCost = "约10万人民币",
            livingCost = "约12万人民币"
        ),
        University(
            id = "u13",
            name = "新南威尔士大学 (UNSW)",
            englishName = "The University of New South Wales",
            abbreviation = "UNSW",
            qsRank = 19,
            country = "澳洲",
            city = "悉尼",
            region = Region.AUSTRALIA,
            description = "新南威尔士大学（The University of New South Wales），简称UNSW，创立于1949年，主校区位于澳大利亚新南威尔士州首府悉尼，是一所公立研究型大学，为澳大利亚八校联盟、环太平洋大学联盟、国际科技大学联盟和Universitas 21成员。",
            websiteUrl = "https://unsw.edu.au",
            logoResId = R.drawable.logo_unsw,
            coverImageUrl = "https://www.unsw.edu.au/content/dam/images/campus/library/2019-06-library-lawn.jpg",
            tags = listOf("澳大利亚八校联盟", "环太平洋大学联盟", "工科强校"),
            interestCount = "3.7w",
            tuitionRange = "4.6-5.6万澳币",
            accommodationCost = "约9万人民币",
            livingCost = "约11万人民币"
        ),
        University(
            id = "u14",
            name = "澳洲国立大学 (ANU)",
            englishName = "The Australian National University",
            abbreviation = "ANU",
            qsRank = 34,
            country = "澳洲",
            city = "堪培拉",
            region = Region.AUSTRALIA,
            description = "澳大利亚国立大学（The Australian National University），简称ANU，是一所位于澳大利亚首都堪培拉的世界著名公立综合性研究型大学，始建于1946年，是澳大利亚第一所研究型大学。",
            websiteUrl = "https://anu.edu.au",
            logoResId = R.drawable.logo_anu,
            coverImageUrl = "https://www.anu.edu.au/files/styles/anu_full_920_518/public/story/University%20Avenue%20ANU.jpg",
            tags = listOf("澳洲八校联盟", "IARU成员", "国家级大学"),
            interestCount = "5w",
            tuitionRange = "4.5-5.5万澳币",
            accommodationCost = "约8万人民币",
            livingCost = "约10万人民币"
        )
    )

    fun getAllUniversities(): List<University> {
        return universities.sortedBy { it.qsRank }
    }

    private val programs = listOf(
        // NUS (u1)
        Program("p1", "u1", "计算机科学硕士", "MSc", 3.5, 6.5, 90, "2026-01-15", "50,000 SGD", listOf("理科", "计算机", "计算机学院")),
        Program("p2", "u1", "数据科学硕士", "MSc", 3.6, 7.0, 95, "2026-01-31", "52,000 SGD", listOf("理科", "数据科学", "理学院")),
        Program("p3", "u1", "人工智能硕士", "MSc", 3.7, 7.0, 95, "2026-02-15", "58,000 SGD", listOf("工科", "AI", "计算机学院")),
        Program("p4", "u1", "金融工程硕士", "MFE", 3.5, 7.0, 100, "2026-01-15", "60,000 SGD", listOf("商科", "金融", "商学院")),
        
        // NTU (u2)
        Program("p5", "u2", "人工智能硕士", "MSc", 3.5, 6.5, 90, "2026-01-31", "52,000 SGD", listOf("工科", "AI", "计算机学院")),
        Program("p6", "u2", "通信工程硕士", "MSc", 3.2, 6.0, 85, "2026-01-31", "45,000 SGD", listOf("工科", "通信", "电机学院")),
        Program("p7", "u2", "分析学硕士", "MSc", 3.4, 6.5, 90, "2026-02-28", "55,000 SGD", listOf("理科", "数据分析", "理学院")),

        // HKU (u3)
        Program("p8", "u3", "计算机科学硕士", "MSc", 3.2, 6.0, 80, "2025-12-31", "240,000 HKD", listOf("工科", "计算机", "工程学院")),
        Program("p9", "u3", "电子工程硕士", "MSc", 3.0, 6.0, 80, "2025-12-31", "220,000 HKD", listOf("工科", "电子", "工程学院")),
        Program("p10", "u3", "数据科学硕士", "MSc", 3.5, 6.5, 85, "2026-01-15", "280,000 HKD", listOf("理科", "数据科学", "理学院")),
        Program("p11", "u3", "商业分析硕士", "MSc", 3.6, 7.0, 90, "2025-11-30", "330,000 HKD", listOf("商科", "商业分析", "商学院")),

        // CUHK (u4)
        Program("p12", "u4", "信息工程硕士", "MSc", 3.0, 6.5, 80, "2026-01-31", "190,000 HKD", listOf("工科", "信息工程", "工程学院")),
        Program("p13", "u4", "金融科技硕士", "MSc", 3.3, 6.5, 85, "2026-02-28", "250,000 HKD", listOf("商科", "金融科技", "工程学院")),
        
        // Imperial (u5)
        Program("p14", "u5", "高级计算硕士", "MSc", 3.8, 7.5, 100, "2026-02-15", "40,000 GBP", listOf("工科", "计算机", "工程学院")),
        Program("p15", "u5", "商业分析硕士", "MSc", 3.7, 7.5, 100, "2026-01-15", "42,000 GBP", listOf("商科", "商业分析", "商学院")),
        
        // Oxford (u6)
        Program("p16", "u6", "计算机科学硕士", "MSc", 3.9, 7.5, 110, "2026-01-10", "38,000 GBP", listOf("理科", "计算机", "理学院")),

        // ETH (u7)
        Program("p17", "u7", "计算机科学硕士", "MSc", 3.7, 7.0, 100, "2025-12-15", "1,500 CHF", listOf("工科", "计算机", "工程学院")),
        
        // MIT (u8)
        Program("p18", "u8", "电气工程与计算机科学", "MEng", 3.9, 7.5, 105, "2025-12-15", "60,000 USD", listOf("工科", "EECS", "工程学院")),
        
        // Stanford (u9)
        Program("p19", "u9", "计算机科学硕士", "MS", 3.9, 7.5, 105, "2025-12-10", "62,000 USD", listOf("工科", "CS", "工程学院")),
        
        // UofT (u10)
        Program("p20", "u10", "应用计算硕士", "MScAC", 3.5, 7.0, 93, "2025-12-15", "50,000 CAD", listOf("理科", "计算机", "理学院")),

        // Australia Programs
        // UniMelb (u11)
        Program("p21", "u11", "信息技术硕士", "MIT", 3.2, 6.5, 80, "2025-10-31", "50,000 AUD", listOf("工科", "IT", "工程学院")),
        Program("p22", "u11", "软件工程硕士", "MSE", 3.3, 6.5, 80, "2025-10-31", "51,000 AUD", listOf("工科", "软件", "工程学院")),
        
        // USYD (u12)
        Program("p23", "u12", "数据科学硕士", "MDS", 3.0, 6.5, 85, "2026-01-15", "54,000 AUD", listOf("理科", "数据科学", "工程学院")),
        Program("p24", "u12", "数字健康硕士", "MSc", 3.0, 6.5, 85, "2026-01-15", "52,000 AUD", listOf("医科", "数字健康", "医学院")),
        
        // UNSW (u13)
        Program("p25", "u13", "工程科学硕士 (IT)", "MEngSc", 3.0, 6.5, 90, "2025-11-30", "52,000 AUD", listOf("工科", "IT", "工程学院")),
        Program("p26", "u13", "光伏工程硕士", "MEngSc", 3.0, 6.5, 90, "2025-11-30", "53,000 AUD", listOf("工科", "新能源", "工程学院")),
        
        // ANU (u14)
        Program("p27", "u14", "计算机硕士", "MComp", 3.3, 6.5, 80, "2025-12-15", "48,000 AUD", listOf("工科", "计算机", "工程学院"))
    )

    fun getUniversitiesByRegion(region: Region): List<University> {
        return universities.filter { it.region == region }.sortedBy { it.qsRank }
    }

    fun getUniversityById(id: String): University? {
        return universities.find { it.id == id }
    }

    fun getProgramsByUniversity(universityId: String): List<Program> {
        return programs.filter { it.universityId == universityId }
    }

    // AI Evaluation Logic (Simplified Rule-based)
    fun evaluateProfile(profile: UserProfile, universityId: String): List<EvaluationResult> {
        val universityPrograms = getProgramsByUniversity(universityId)
        val university = getUniversityById(universityId) ?: return emptyList()

        return universityPrograms.map { program ->
            val (tag, prob, suggestions) = calculateAdmissionChance(profile, program)
            EvaluationResult(program, university, tag, prob, suggestions)
        }
    }

    private fun calculateAdmissionChance(profile: UserProfile, program: Program): Triple<AdmissionTag, Int, List<String>> {
        var score = 0
        val suggestions = mutableListOf<String>()

        // GPA Check (Max 40)
        if (profile.gpa >= program.minGpa) {
            score += 40
        } else {
            val gap = program.minGpa - profile.gpa
            val potentialGain = 20
            score += 20
            suggestions.add("GPA低于往年平均 (${program.minGpa})。建议提升GPA至${program.minGpa} (预计录取概率提升 +${potentialGain}%)")
        }

        // Language Check (Max 30)
        if (profile.ieltsScore >= program.minIelts || profile.toeflScore >= program.minToefl) {
            score += 30
        } else {
            val potentialGain = 20
            score += 10
            suggestions.add("语言成绩未达标 (需 雅思${program.minIelts} 或 托福${program.minToefl})。达标后预计录取概率提升 +${potentialGain}%")
        }

        // Background Check (Max 30)
        if (profile.hasResearchExp) {
            score += 15
        } else {
            suggestions.add("缺乏科研经历。补充相关科研项目预计录取概率提升 +15%")
        }
        
        if (profile.hasInternshipExp) {
            score += 15
        } else {
            suggestions.add("缺乏实习经历。补充相关实习预计录取概率提升 +15%")
        }

        val probability = score.coerceIn(10, 99) // Base 10%
        val tag = when {
            probability >= 80 -> AdmissionTag.SAFETY
            probability >= 50 -> AdmissionTag.MATCH
            else -> AdmissionTag.REACH
        }

        return Triple(tag, probability, suggestions)
    }
}
