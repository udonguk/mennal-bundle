import {Box, Card, CardContent, Stack, Typography} from "@mui/material";
import MyResponsiveRadar from "../../components/graph/MyResponsiveRadar";
import React, {useContext} from "react";
import {observer} from "mobx-react";
import {StoresContext} from "../../store/RootStore";
import OtherSurveyLinks from "./OtherSurveyLinks";

const Result = observer(() => {
    const store = useContext(StoresContext);
    const surveyItemStore = store.surveyItemStore;

    return (
      <Box sx={{marginTop: 3}}>
          <Typography variant={"h5"}>
              {surveyItemStore.result.categoryType}
          </Typography>
          <Box maxWidth
               height={300}
          >
              <MyResponsiveRadar data={surveyItemStore.graphFormat}/>
          </Box>
          <Stack spacing={1} pt={3} pb={1}>
              {surveyItemStore.result.factionList.map((item, index) => {
                  return (
                    <Card variant={'outlined'}
                          key={`result_card_${index}`}>
                        <CardContent>
                            <Typography variant={"subtitle1"} color={"text.primary"}>
                                {item.faction}
                            </Typography>
                            <Typography variant={"body1"} color={"text.secondary"}>
                                {item.title}
                            </Typography>
                        </CardContent>
                    </Card>
                  )
              })}
          </Stack>
        <OtherSurveyLinks />
      </Box>
    )
  }
)
export default Result;