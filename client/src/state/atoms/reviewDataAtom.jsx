import { atom } from "recoil";
// import profile from ../../Component/style/img/profile.png;

export const reviewDataAtom = atom({
  key: "data",
  default: [
    // {
    //   reviewId: 2,
    //   title: "ㅁㅁsdfsdfsㅁㅁㅁ",
    //   content:
    //     "이 카페의 음료는 정말 맛있었어요. 풍부한 향과 훌륭한 조화로운 맛을 느낄 수 있었습니다. 하지만 가격이 다소 비싸서 아쉬웠어요. 음료의 퀄리티는 높았지만 가격 대비해서는 조금 아쉬웠던 경험이었습니다.",
    //   photo: [],
    //   createdAt: "",
    //   modifiedAt: "",
    //   rating: "HATE",
    //   member: {
    //     memberId: 3,
    //     nickName: "dmadma",
    //     photo: null,
    //   },
    // },
  ],
});
