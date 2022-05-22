import {Box, Card, CardContent, Stack, Typography} from "@mui/material";
import MyResponsiveRadar from "../../components/graph/MyResponsiveRadar";
import React, {useContext} from "react";
import {observer} from "mobx-react";
import {StoresContext} from "../../store/RootStore";
import OtherSurveyLinks from "./OtherSurveyLinks";
import MyResponsiveBar from "../../components/graph/MyResponsiveBar";
import MyResponsiveRangeBar from "../../components/graph/MyResponsiveRangeBar";

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
          {/*<MyResponsiveBar data={[*/}
          {/*  {*/}
          {/*    "country": "내향성/외향성",*/}
          {/*    "hot dog": 3,*/}
          {/*    "hot dogColor": "hsl(186, 70%, 50%)",*/}
          {/*    "burger": 6,*/}
          {/*    "burgerColor": "hsl(136, 70%, 50%)",*/}
          {/*  },*/}
          {/*  {*/}
          {/*    "country": "실제형/상상형",*/}
          {/*    "hot dog": 4,*/}
          {/*    "hot dogColor": "hsl(186, 70%, 50%)",*/}
          {/*    "burger": 5,*/}
          {/*    "burgerColor": "hsl(136, 70%, 50%)",*/}
          {/*  },*/}
          {/*  {*/}
          {/*    "country": "사고형/감정형",*/}
          {/*    "hot dog": 7,*/}
          {/*    "hot dogColor": "hsl(186, 70%, 50%)",*/}
          {/*    "burger": 2,*/}
          {/*    "burgerColor": "hsl(136, 70%, 50%)",*/}
          {/*  },*/}
          {/*  {*/}
          {/*    "country": "조직형/유연성",*/}
          {/*    "hot dog": 0,*/}
          {/*    "hot dogColor": "hsl(186, 70%, 50%)",*/}
          {/*    "burger": 9,*/}
          {/*    "burgerColor": "hsl(136, 70%, 50%)",*/}
          {/*  }*/}
          {/*]}/>*/}

          {'bar' === surveyItemStore.graphType
            && <MyResponsiveBar data={surveyItemStore.barGraphFormat}
                                keys={surveyItemStore.barGraphKeys}/>}

          {'range' === surveyItemStore.graphType
            && <MyResponsiveRangeBar data={surveyItemStore.barRangeGraphFormat}
                                     keys={surveyItemStore.barRangeGraphKeys}
                                     maxValue={10}
            />}

          {'radar' === surveyItemStore.graphType &&
            <MyResponsiveRadar data={surveyItemStore.graphFormat}/>}
        </Box>
        <Stack spacing={1} pt={3} pb={1}>
          {surveyItemStore.surveyResult
            && surveyItemStore.surveyResult.map((item, index) => {
              return (
                <Card variant={'outlined'}
                      key={`result_card_${index}`}>
                  <CardContent>
                    <Typography variant={"subtitle1"} color={"text.primary"}>
                      {item.title}
                    </Typography>
                    <Typography variant={"body1"} color={"text.secondary"}>
                      {item.content}
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