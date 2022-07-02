import {Box, Card, CardContent, CircularProgress, Divider, Grid, Stack, Typography} from "@mui/material";
import MyResponsiveRadar from "../../components/graph/MyResponsiveRadar";
import React, {useContext, useMemo} from "react";
import {observer} from "mobx-react";
import {StoresContext} from "../../store/RootStore";
import OtherSurveyLinks from "./OtherSurveyLinks";
import MyResponsiveBar from "../../components/graph/MyResponsiveBar";
import MyResponsiveRangeBar from "../../components/graph/MyResponsiveRangeBar";
import ResultInfosBasic from "./basic/ResultInfosBasic";
import ResultInfos04 from "./04/ResultInfos04";
import Statistic from "./Statistic";

const Result = observer(() => {
    const store = useContext(StoresContext);
    const surveyItemStore = store.surveyItemStore;

    useMemo(() => {
      console.debug('aaaaa',surveyItemStore.primaryFactions)
    }, [surveyItemStore.primaryFactions])

    return (<>
        <Box sx={{marginTop: 3}}>
          {surveyItemStore.isResultLoading ?
            <Box sx={{ display: 'flex',justifyContent: 'center' }}>
              <CircularProgress />
            </Box>
             :
            <>
              <Typography variant={"h5"}>
                {surveyItemStore.result.categoryType}
              </Typography>
              <Box maxWidth
                   height={300}
              >
                {'bar' === surveyItemStore.graphType
                  && <MyResponsiveBar data={surveyItemStore.barGraphFormat}
                                      keys={surveyItemStore.barGraphKeys}/>
                }

                {'range' === surveyItemStore.graphType
                  && <MyResponsiveRangeBar data={surveyItemStore.barRangeGraphFormat}
                                           keys={surveyItemStore.barRangeGraphKeys}
                                           maxValue={10}/>
                }

                {'radar' === surveyItemStore.graphType &&
                  <MyResponsiveRadar data={surveyItemStore.graphFormat}/>}
              </Box>
              <Statistic />
              <Stack spacing={1} pt={3} pb={1}>
                {'자기조절능력' === surveyItemStore.result.categoryType ? <ResultInfos04 /> : <ResultInfosBasic />}
              </Stack>
              <OtherSurveyLinks/>
            </>
          }
        </Box>
      </>
    )
  }
)
export default Result;
