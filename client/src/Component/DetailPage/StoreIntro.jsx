import { useEffect, useState } from "react";
import styled from "styled-components";
import defaultImg from "../style/img/defaultImg.png";
import { api } from "../../Util/api";

const Container = styled.div`
  margin: auto;
  width: 1200px;
`;

const Image = styled.img`
  width: 1200px;
  height: 223px;
  border-radius: 30px;
  border: none;
  margin-top: 20px;
`;

const Intro = styled.div`
  margin-top: 20px;
  flex-wrap: wrap;
  font-size: var(--medium-font);
  margin-bottom: 60px;
`;

const StoreIntro = () => {
  const [data, setData] = useState({
    photo: defaultImg,
    content: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/restaurants/1");
        const { photo, content } = response.data;
        const newData = {
          photo: photo || defaultImg,
          content,
        };
        setData(newData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  return (
    <Container>
      <Image src={data.photo} alt="defaultImg" />
      <Intro>{data.content}</Intro>
    </Container>
  );
};

export default StoreIntro;
