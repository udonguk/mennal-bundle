import SurveyItem from "./component/ServeyItem";
import {Stack, Typography} from "@mui/material";
import React, {useContext, useEffect, useLayoutEffect} from "react";
import {observer} from "mobx-react";
import {useLocation} from "react-router-dom";
import {StoresContext} from "../../store/RootStore";
import _ from "lodash";

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
    surveyItemStore.loadSurveyItemsByCategory(categoryId)
  }, [location])

  return (
    <>
      {_.isNil(surveyItemStore.survey.surveySubCategories) && <Typography>Empty</Typography>}
      {!_.isNil(surveyItemStore.survey.surveySubCategories) &&
        <Stack spacing={1} pt ={3} pb={1}>
          { surveyItemStore.survey.surveySubCategories.map((surveySubCategory) => {
            return surveySubCategory.surveyItems.map((item, index) =>
              <SurveyItem key={item.id} index={index} item={item}/>
            )
          })}
        </Stack>
      }
    </>
  )
})


export default SurveyItems;