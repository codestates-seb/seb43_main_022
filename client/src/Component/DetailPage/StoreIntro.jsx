import styled from "styled-components";

const Container = styled.div`
  margin: auto;
  width: 1200px;
`;

const Image = styled.img`
  width: 1200px;
  height: 223px;
  /* border-radius: 30px;
  border: 1px solid; */
  margin-top: 20px;
`;

const Intro = styled.div`
  margin-top: 20px;
  flex-wrap: wrap;
  font-size: var(--medium-font);
  margin-bottom: 60px;
  white-space: pre-wrap; // 줄바꿈을 유지하면서 공백도 유지
  word-wrap: break-word; // 긴 단어가 줄바꿈을 유발하도록 함
`;

const StoreIntro = () => {
  return (
    <Container>
      <Image />
      <Intro>
        안녕하세요 'OPM(오픈마인드)' 입니다. 이름부터가 고민이 많았지만 우리가
        느낀 그대로를 표현하고 싶었습니다. '온전하고 자유로운 삶 그대로를'
        전달해 드릴 예정입니다. 우리와 함께 카페 오픈마인드에서 우리의 긍정적인
        시너지를 받아 가시고 우리에게 긍정적인 에너지를 주시면 더할 나위 없이
        좋은 공간이 될 것 같습니다. 많은 사람들이 남녀노소 누구나가 너 나 할 것
        없이 아무 제약 없이 정해진 것 없이 자유롭게 즐길 수 있습니다.
        '어서오세요. 카페 오픈마인드 입니다'
      </Intro>
    </Container>
  );
};

export default StoreIntro;
