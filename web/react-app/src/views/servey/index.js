import React, {useContext} from "react";
import SurveyItem from "./component/ServeyItem";
import {Box, Button, Stack} from "@mui/material";
import {GlobalStoreContext} from "../../store/GlobalStoreProvider";
import {observer} from "mobx-react";
import LinearProgressWithLabel from "../../components/LinearProgressWithLabel";

const Survey = observer(() => {
  // const surveyItems = getSurveyItems();
  const store = useContext(GlobalStoreContext);
  const surveyItemStore = store.surveyItemStore
  // let location = useLocation();

  return (
    <Box>
      <Box
        sx={{
          width: '100%',
          position: 'fixed',
          left: '50%',
          transform: 'translate(-50%, 0)',
          zIndex: 1050,
          backgroundColor: 'rgba( 64, 64, 64, 0.2 )',
        }}
      >
        <LinearProgressWithLabel
          value ={surveyItemStore.progressPercent}
          info ={`${surveyItemStore.checkCount} / ${surveyItemStore.itemCount}`}
        />
      </Box>
      <Stack spacing={1} pt ={3} pb={1}>
        {surveyItemStore.surveyItems.map((item, index) =>
          <SurveyItem key={item.id} index={index} item={item}/>
        )}
      </Stack>
      <Button variant={'contained'}
              disabled={100 !== surveyItemStore.progressPercent}
              fullWidth
      >
        결과보기
      </Button>
    </Box>
  )
})

export default Survey;