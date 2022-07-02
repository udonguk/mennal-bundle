import React, {useContext} from "react";
import {StoresContext} from "../../store/RootStore";
import {Box, Card, CardContent, Divider, Grid, Typography} from "@mui/material";
import {observer} from "mobx-react";

const Statistic = observer(() => {
  const store = useContext(StoresContext);
  const surveyItemStore = store.surveyItemStore;

  return <Box>
      <Grid container spacing={1} >
        {surveyItemStore.result.statisticList.map((item, index) =>
          <Grid item xs={6}>
            <Card variant='outlined'
                  key={`statistic_card_${index}`}
                  sx={!surveyItemStore.primaryFactions.includes(item.optionType)
                    && { background :  '#DCDCDC'}}
            >
              <CardContent>
                <Box sx={{ display: 'flex',justifyContent: 'space-evenly' }}>
                  <Typography variant="body2"
                              sx={!surveyItemStore.primaryFactions.includes(item.optionType)
                                && { color :  '#A9A9A9'}}
                  >
                    {item.optionType}
                  </Typography>
                  <Divider light orientation='vertical' flexItem />
                  <Typography variant="body2"
                              sx={!surveyItemStore.primaryFactions.includes(item.optionType)
                                && { color :  '#A9A9A9'}}
                  >
                    {'bar' === surveyItemStore.graphType ?
                      `${Math.floor((item.checked / item.total) * 100) }%`
                      : `${Math.floor((item.checked / item.total) * 10) }`}
                  </Typography>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        )}
      </Grid>
    </Box>
})

export default Statistic
