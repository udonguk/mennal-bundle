import {observer} from "mobx-react";
import React, {useContext} from "react";
import {StoresContext} from "../../../store/RootStore";
import {Card, CardContent, CardHeader, CardMedia, Divider, Grid, Stack, Typography} from "@mui/material";

const ResultInfos04 = observer(() => {
  const store = useContext(StoresContext);
  const surveyItemStore = store.surveyItemStore;
  const surveyResult = surveyItemStore.surveyResult

  const titleSet = new Set(surveyResult.map(item => item.title))
  const titleList = [...titleSet]

  return <>
    <Stack spacing={2}>
      <Card variant="outlined">
        <CardMedia
          component='img'
          width={"100%"}
          image = 'self_control.png'/>
      </Card>
      <Card variant="outlined">
        <CardHeader title={'학습목표'}/>
        <CardContent>
          학습목표가 뚜렷하게 설정되어 있는 학생의 경우에는 자신이 도달되어야 할 것에 대한 기대를 형성시켜주어 적극정으로 학업에 힘할 수 있게 해줍니다.
          새로운 것을 익힌다거나 어떠한 보상물에 대한 획득욕구, 주변 친구들과의 경쟁에서의 승리감 등 여러 가지의 목표를 뚜렷하게 가지고 있는 것은 학습에 대한 태도나 흥미, 가치부여,지속성 등과 같은 학습 동기에 큰 영향을 미칩니다.
          학생의 목표설정이 뚜렷한 경우라도 부모님 혹은 선생님이 함께 단기적인 목표를 선정하고 차례차례 목표를 달성할 수 있도록 도와주는 것은 학생에게 중요한 영향을 줄 것입니다.
        </CardContent>
      </Card>
      <Card variant="outlined">
        <CardHeader title={'학습계획성'}/>
        <CardContent>
          학습계획성이 높은 학생은 스스로 효과적으로 학습에 대해 준비하고 체계적으로 세우는 것이 익숙한 학생입니다.
          학습에 대한 준비성도 높으며 책임감 또한 갖고 있습니다. 계획적인 학습습관은 보다 많은 양과 복잡한 내용을 다루게 되는 고학년의 학습에 필수적으로 중요한 요소입니다.
          계속적으로 학습에 대한 철저한 계획을 해갈 수 있도록 지도가 필요합니다.
        </CardContent>
      </Card>
      <Card variant="outlined">
        <CardHeader title={'실천력'}/>
        <CardContent>
          실천력이 높은 학생은 자신이 세운 계획을 실행으로 옮기는 의지가 매우 강한 학생입니다.
          올바른 학습계획과 전략을 구상하는 일도 중요하지만 그보다도 더욱 중요한 것은 그것을 실천하려는 학생 스스로의 의지입니다.
          실천력이 높은 학생의 경우에는 이제 학생 스스로 실행한 계획이 올바르게 짜여있으며 결과가 어떻게 이루어져 왔는지 돌아보는 검토의 시간이 필요할 것입니다.
        </CardContent>
      </Card>
      <Divider />
      {titleList.map((title, index) => {
        return (
            <Card variant="outlined" key={`result_${index}`}>
              <CardHeader title={title}/>
              <CardContent>
                <Grid container
                      direction="row"
                      justifyContent="center"
                      alignItems="center"
                >
                  <Grid item xs={10}>
                    <Grid container
                          direction="row"
                          rowSpacing={1}
                          justifyContent="center"
                          alignItems="center"
                    >
                      {surveyResult.map((result, index2) => {
                        if(title === result.title){
                          return (
                            <Grid item>
                              <Card variant="outlined" key={`result_sub_${index2}`}>
                                <CardContent>
                                  <Grid container direction="row" >
                                    <Grid item>
                                      <Typography variant="subtitle2" color="text.primary">
                                        {result.subTitle}
                                      </Typography>
                                    </Grid>
                                    <Grid item>
                                      <Typography variant="body1" color="text.primary">
                                        {result.content}
                                      </Typography>
                                    </Grid>
                                  </Grid>
                                </CardContent>
                              </Card>
                            </Grid>
                          )
                        }
                      })}
                    </Grid>
                  </Grid>
                </Grid>
              </CardContent>
            </Card>
        )
      })}
    </Stack>
  </>
})

export default ResultInfos04
