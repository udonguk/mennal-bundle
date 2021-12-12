import SurveyItem from "./component/ServeyItem";
import {Stack} from "@mui/material";
import React, {useContext, useLayoutEffect} from "react";
import {observer} from "mobx-react";
import {useLocation} from "react-router-dom";
import {StoresContext} from "../../store/RootStore";

const SurveyItems = observer(() => {
  const store = useContext(StoresContext);
  const surveyItemStore = store.surveyItemStore;
  const surveyCategoryStore = store.surveyCategoryStore;

  const location = useLocation();

  const getCategory = () => {
    const lastSeparatorIndex = location.pathname.lastIndexOf('/');
    return location.pathname.substr(lastSeparatorIndex +1);
  }

  useLayoutEffect(() => {
    const categoryId = surveyCategoryStore.getSurveyCategory(getCategory())
    surveyItemStore.loadSurveyItemsByCategory(getCategory())
  }, [location])

  return (
    <Stack spacing={1} pt ={3} pb={1}>
      {surveyItemStore.surveyItems
        .map((item, index) =>
          <SurveyItem key={item.id} index={index} item={item}/>
        )}
      {/*{getSurveyItems().map((item, index) =>*/}
      {/*    <SurveyItem key={item.id} index={index} item={item}/>*/}
      {/*  )}*/}
    </Stack>
  )
})


export default SurveyItems;