import { atom } from "recoil";

export const keywordsAtom = atom({
  key: "keywords",
  default: [
    "중식",
    "한식",
    "일식",
    "양식",
    "분식",
    "매운거",
    "안매운거",
    "아이와 함께",
    "데이트",
    "랜덤",
  ],
});

export const storesState = atom({
  key: "restaurant",
  default: [
    {
      restaurantId: 1,
      createdAt: "2023-05-19T06:24:00Z",
      modifiedAt: "2023-05-19T08:00:00Z",
      restaurantName: "식당명",
      content: "식당 설명",
      streetAddress: "도로명주소",
      detailAddress: "상세주소",
      latitude: 12.12312313,
      longitude: 12.3123123,
      tel: "1234-1234",
      open_time: "10:00 AM ~ 10:00 PM",
      category: "음식 종류",
      photoUrl: "https://amazon.s3.url/photo.jpg",
      totalViews: 0,
      totalReview: 45,
      totalFavorite: 24,
      rating: 4.5,
      tag: [
        {
          tagId: 1,
          name: "#햄버거",
        },
        {
          tagId: 2,
          name: "#버거",
        },
      ],
      pageInfo: {
        page: 1,
        size: 4,
        totalElements: 2,
        totalPages: 1,
      },
    },
    {
      restaurantId: 2,
      createdAt: "2023-05-19T06:24:00Z",
      modifiedAt: "2023-05-19T08:00:00Z",
      name: "식당명2",
      content: "식당 설명2",
      streetAddress: "도로명주소",
      detailAddress: "상세주소",
      latitude: 12.12312313,
      longitude: 12.3123123,
      tel: "1234-1234",
      open_time: "10:00 AM ~ 10:00 PM",
      category: "음식 종류",
      photoUrl: "https://amazon.s3.url/photo.jpg",
      totalViews: 0,
      totalReview: 45,
      totalFavorite: 24,
      rating: 4.5,
      tag: [
        {
          tagId: 1,
          name: "#햄버거",
        },
        {
          tagId: 2,
          name: "#버거",
        },
      ],
      pageInfo: {
        page: 1,
        size: 4,
        totalElements: 2,
        totalPages: 1,
      },
    },
  ],
});
