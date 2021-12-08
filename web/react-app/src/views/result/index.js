import {Box, Typography} from "@mui/material";
import MyResponsiveRadar from "../../components/graph/MyResponsiveRadar";
import React from "react";

const tempGraphData = [
  {
    "faction": "학습의지",
    "chardonay": 31,
  },
  {
    "faction": "학습행동",
    "chardonay": 40,
  },
  {
    "faction": "학습신념",
    "chardonay": 34,
  },
];


const Result = () => {
  return (
    <>
      <Box maxWidth height={500}>
        <MyResponsiveRadar data={tempGraphData} />
      </Box>
      <Typography variant={"body"} color={"text.primary"}>
        자아효능감이 적절한 편입니다.
      </Typography>
      <Typography variant={"body2"} color={"text.secondary"}>
        자아효능감이 높을수록 시험에 대한 불안함이 적으며, 효과적인 학습전략을 세우거나 합리적인 목표설정을 잘하고 자기통제력 또한 우수합니다. 높은 수준의 자아효능감을 가지고 있는 당신은 어렵고 도전적인 과제를 선택하기를 좋아하여 높은 난이도의 문제를 접하였을 때는 해결할 때까지 끈질기게 몰아붙이는 근성도 지니고 있습니다. 자신이 생각하기에 어려울 것으로 기대되는 문제는 더욱 흥미를 느끼고 많은 시간을 할애하여 집중력을 발휘합니다. 따라서 그에 따른 높은 성취감 또한 느낄 수 있습니다. 즉, 당신은 어려운 문제에 부딪혔을 때 자아효능감이 발휘되어 그 어려움을 극복하고 성공적인 결과를 이룰 가능성이 높습니다. 하지만 자아효능감이 높은 사람들은 스스로 너무 쉽다고 생각하는 문제나 과제물에는 자기도 모르게 노력하지 않거나 대충하려는 경향이 있습니다. 혹시, 시험문제 중에서 어려운 문제가 아니라 너무 쉬운 문제에서 자주 실수를 해서 속상했던 기억이 있다면 당신 또한 이런 경향이 있는 것입니다. 어려운 문제를 훌륭히 풀어낼 능력을 지니고 있는 만큼, 쉬운 문제에도 집중하고 검토하는 주의를 기울이는 것이 좋습니다.
      </Typography>
    </>
  )
}

export default Result;