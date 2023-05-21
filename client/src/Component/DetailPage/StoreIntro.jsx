import { useEffect, useState } from "react";
import styled from "styled-components";
import defaultImg from "../style/img/defaultImg.png";
import { api } from "../../Util/api";
import { useParams } from "react-router-dom";

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
  const { res_id } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get(`/restaurants/${res_id}`);
        const { photoUrl, content } = response.data;
        const newData = {
          photoUrl: photoUrl || defaultImg,
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
      <Image src={data.photoUrl} alt="defaultImg" />
      <Intro>{data.content}</Intro>
    </Container>
  );
};

export default StoreIntro;
