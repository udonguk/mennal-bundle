import React, {useContext} from "react";
import {Button, Stack} from "@mui/material";
import {Link} from "react-router-dom";
import {observer} from "mobx-react";
import {StoresContext} from "../../store/RootStore";

const OtherSurveyLinks = observer(() => {
    const store = useContext(StoresContext);
    const surveyItemStore = store.surveyItemStore;
    const surveyCategoryStore = store.surveyCategoryStore;

    return (
      <Stack spacing={1} pt={3} pb={1}>
        {surveyCategoryStore.surveyCategories
          .filter(item => item.id !== surveyItemStore.result.categoryId)
          .map((item) => {
            return (
              <Button variant="contained"
                      component={Link}
                      to={`/survey/${item.code}`}
              >
                {`${item.title} 테스트 하기`}
              </Button>
            )
          })}
      </Stack>
    )
  }
)
export default OtherSurveyLinks;