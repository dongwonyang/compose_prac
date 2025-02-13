package com.example.compose_prac.ui.data

data class Wish(
    val id: Long = 0,
    val title: String = "",
    val description: String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(1, "여행 가기", "올해 안에 제주도로 여행 떠나기"),
        Wish(2, "책 읽기", "1년에 최소 20권의 책 읽기"),
        Wish(3, "운동 시작", "매주 3번 이상 헬스장 가기"),
        Wish(4, "외국어 공부", "영어 회화 마스터하기"),
        Wish(5, "요리 배우기", "한 달에 한 가지 새로운 요리 도전"),
        Wish(6, "악기 배우기", "기타 연주 가능해지기"),
        Wish(7, "저축 목표", "1년 동안 500만 원 저축하기"),
        Wish(8, "새로운 취미 찾기", "캘리그라피나 드로잉 도전"),
        Wish(9, "자격증 취득", "올해 안에 토익 900점 이상"),
        Wish(10, "친구들과 여행", "친구들과 해외여행 계획하기")
    )
}
