import React, {useContext} from "react";
import {Box, Button} from "@mui/material";
import {GlobalStoreContext} from "../../store/GlobalStoreProvider";
import {observer} from "mobx-react";
import {Outlet, useNavigate} from "react-router-dom";
import LinearProgressWithLabel from "../../components/LinearProgressWithLabel";

const Survey = observer(() => {
  const navigate = useNavigate();
  const store = useContext(GlobalStoreContext);
  const surveyItemStore = store.surveyItemStore

  const showResultOnClick = () => {
    navigate('/result')
  }

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
      <Outlet id={'surveyItems'}/>
      <Button variant={'contained'}
        // disabled={100 !== surveyItemStore.progressPercent}
              onClick={showResultOnClick}
              fullWidth
      >
        결과보기
      </Button>
    </Box>
  )
})

export default Survey;