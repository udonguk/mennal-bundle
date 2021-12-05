import SurveyItem from "./component/ServeyItem";
import {Stack} from "@mui/material";
import React, {useContext, useLayoutEffect} from "react";
import {observer} from "mobx-react";
import {GlobalStoreContext} from "../../store/GlobalStoreProvider";
import {useLocation} from "react-router-dom";

const SurveyItems = observer(() => {
  const store = useContext(GlobalStoreContext);
  const surveyItemStore = store.surveyItemStore;
  const location = useLocation();

  const getCategory = () => {
    const lastSeparatorIndex = location.pathname.lastIndexOf('/');
    return location.pathname.substr(lastSeparatorIndex +1);
  }

  useLayoutEffect(() => {
    surveyItemStore.loadSurveyItemsByCategory(getCategory())
  }, [location])

  return (
    <Stack spacing={1} pt ={3} pb={1}>
      {surveyItemStore.surveyItems
        .map((item, index) =>
          <SurveyItem key={item.id} index={index} item={item}/>
        )}
    </Stack>
  )
})


export default SurveyItems;