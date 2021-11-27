import React from "react";
import SurveyItem from "./component/ServeyItem";
import {Stack} from "@mui/material";
import {getSurveyItems} from "../../dump/surveyItemDump";

const Survey = () => {
  const surveyItems = getSurveyItems();

  return (
    <Stack spacing={1}>
      {surveyItems.map((item, index) =>
        <SurveyItem key={item.code} index={index} item={item}/>
      )}
    </Stack>
  )
}

export default Survey;