import React, {useContext, useEffect} from "react";
import SurveyItem from "./component/ServeyItem";
import {Button, Stack} from "@mui/material";
import {getSurveyItems} from "../../dump/surveyItemDump";
import {GlobalStoreContext} from "../../store/GlobalStoreProvider";
import {observer} from "mobx-react";

const Survey = observer(() => {
  // const surveyItems = getSurveyItems();
  const store = useContext(GlobalStoreContext);
  const surveyItemStore = store.surveyItemStore
  console.debug(surveyItemStore)

  useEffect(() => {
    // surveyItemStore.getTest()
  }, [])

  return (
    <>
      <Stack spacing={1}>
        {surveyItemStore.surveyItems.map((item, index) =>
          <SurveyItem key={item.id} index={index} item={item}/>
        )}
      </Stack>
      <Button variant={'contained'}
              fullWidth
      >
        결과보기
      </Button>
    </>
  )
})

export default Survey;