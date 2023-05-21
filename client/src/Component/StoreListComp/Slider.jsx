import { useState, useEffect } from "react";
import styled from "styled-components";
import SlideImg1 from "../style/img/SlideImg1.jpg";
import SlideImg2 from "../style/img/SlideImg2.jpg";
import SlideImg3 from "../style/img/SlideImg3.jpg";
const Container = styled.div`
  width: 400px;
  background-size: cover;
  border-radius: 20px;
  overflow: hidden;
  position: relative;
  display: flex;
  justify-content: center;
`;
const ButtonWrap = styled.div`
  display: flex;
  justify-content: center;
  position: absolute;
  bottom: 20px;
  right: 20px;
  gap: 6px;
`;
const Button = styled.button`
  all: unset;
  padding: 6px 11px 8px 11px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 50px;
  font-weight: 400;
  &:hover {
    transition: all 0.1s ease-in-out;
    background-color: var(--eatsgreen);
    color: var(--white);
    cursor: pointer;
  }
`;
const SliderContainer = styled.div`
  width: 100%;
`;

export default function Slider() {
  const images = [SlideImg1, SlideImg2, SlideImg3];

  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) =>
        prevIndex === images.length - 1 ? 0 : prevIndex + 1,
      );
    }, 3000);
    return () => clearInterval(interval);
  }, [images.length]);

  const goToPrevImage = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1,
    );
  };

  const goToNextImage = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === images.length - 1 ? 0 : prevIndex + 1,
    );
  };

  return (
    <Container>
      <SliderContainer>
        <img src={images[currentIndex]} alt="slider" />
      </SliderContainer>
      <ButtonWrap>
        <Button onClick={goToPrevImage}>&lt;</Button>
        <Button onClick={goToNextImage}>&gt;</Button>
      </ButtonWrap>
    </Container>
  );
}
