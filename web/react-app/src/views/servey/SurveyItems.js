import SurveyItem from "./component/ServeyItem";
import {Box, CircularProgress, Stack} from "@mui/material";
import React, {useContext, useLayoutEffect} from "react";
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

  const getItems = () => {
    let itemCount = 0
    return <>
      {surveyItemStore.survey.surveySubCategories.map((surveySubCategory) => {
        return surveySubCategory.surveyItems.map(item =>
          <SurveyItem key={item.id} index={itemCount++} item={item}/>
        )
      })}
    </>;
  }
  return (
    <>
      {_.isNil(surveyItemStore.survey.surveySubCategories) &&
        <Box sx={{ display: 'flex',justifyContent: 'center' }}>
          <CircularProgress />
        </Box>
      }
      {!_.isNil(surveyItemStore.survey.surveySubCategories) &&
        <Stack spacing={1} pt={3} pb={1}>
          {getItems()}
        </Stack>
      }
    </>
  )
})


export default SurveyItems;
