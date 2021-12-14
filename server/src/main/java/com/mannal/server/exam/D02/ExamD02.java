package com.mannal.server.exam.D02;

import com.mannal.server.dto.FactionDto;
import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;
import com.mannal.server.entity.survey.SurveySubCategoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ExamD02 {

    public SurveyResultDto getResult(final SurveyEntity surveyEntity) {
        List<FactionDto> factionDtos = new ArrayList<>();

        FactionDto factionDto01 =  cacluration1(getResultBySubCode("01", surveyEntity));
        FactionDto factionDto02 =  cacluration2(getResultBySubCode("02", surveyEntity));
        FactionDto factionDto03 =  cacluration3(getResultBySubCode("03", surveyEntity));

        factionDtos.add(factionDto01);
        factionDtos.add(factionDto02);
        factionDtos.add(factionDto03);

        return SurveyResultDto.builder()
                .categoryType("02")
                .factionList(factionDtos)
                .build();
    }


    public FactionDto cacluration1(List<SurveyResultEntity> surveyResultEntities){
        AtomicInteger score = new AtomicInteger();
        score.addAndGet(this.getSumScore(surveyResultEntities));

        String itemType = "01";
        String resultType;
        String title;

        if(5 < score.get()){
            resultType = "01";
            title = "자아효능감이 높을수록 시험에 대한 불안함이 적으며, 효과적인 학습전략을 세우거나 합리적인 목표설정을 잘하고 자기통제력 또한 우수합니다. 높은 수준의 자아효능감을 가지고 있는 당신은 어렵고 도전적인 과제를 선택하기를 좋아하여 높은 난이도의 문제를 접하였을 때는 해결할 때까지 끈질기게 몰아붙이는 근성도 지니고 있습니다. 자신이 생각하기에 어려울 것으로 기대되는 문제는 더욱 흥미를 느끼고 많은 시간을 할애하여 집중력을 발휘합니다. 따라서 그에 따른 높은 성취감 또한 느낄 수 있습니다. 즉, 당신은 어려운 문제에 부딪혔을 때 자아효능감이 발휘되어 그 어려움을 극복하고 성공적인 결과를 이룰 가능성이 높습니다. 하지만 자아효능감이 높은 사람들은 스스로 너무 쉽다고 생각하는 문제나 과제물에는 자기도 모르게 노력하지 않거나 대충하려는 경향이 있습니다. 혹시, 시험문제 중에서 어려운 문제가 아니라 너무 쉬운 문제에서 자주 실수를 해서 속상했던 기억이 있다면 당신 또한 이런 경향이 있는 것입니다. 어려운 문제를 훌륭히 풀어낼 능력을 지니고 있는 만큼, 쉬운 문제에도 집중하고 검토하는 주의를 기울이는 것이 좋습니다.";
        } else if(4 >= score.get() && score.get() <= 3) {
            resultType = "02";
            title = "자아효능감이 높을수록 시험에 대한 불안함이 적으며, 효과적인 학습전략을 세우거나 합리적인 목표설정을 잘하고 자기통제력 또한 우수합니다. 적절한 수준의 자아효능감을 지니고 있는 당신은 어렵고 힘든 문제를 접하게 되어도 피하려고 하지 않고 도전적으로 시도해보려는 긍정적인 자세를 가지고 있습니다. 피하고 포기하는 것보다는 끈질기게 몰아붙이는 근성을 가지고 있기 때문에 어려운 수준의 학습에서도 좋은 성취감과 성적을 거둘 수 있는 가능성을 충분히 가지고 있습니다. 학습에 대한 본인 스스로의 자신감이 되어주는 자아효능감은 공부를 하는데 있어 반드시 갖추고 있어야 할 마음가짐입니다. 적절한 자아효능감을 가지고 있는 당신이지만 혹시 아직 자신감이 부족한 부분이 있지는 않은가요? 많은 부분에서 충분한 역량을 발휘하고 있는 당신은 자신 없는 부분에 대해서도 충분히 잘 할 수 있는 능력을 지니고 있습니다. 자신 없는 부분에서 더욱 자신감을 갖고 행동한다면 최고의 성적과 성취감을 느낄 수 있는 좋은 결과로 나타날 것입니다.";
        } else {
            resultType = "03";
            title = "자아효능감이 부족한 당신은 일반적으로 학습에 대한 자심감이 많이 결여되어 있는 상태입니다. 문제에 집중을 제대로 하지 못하고 다른 생각을 한다거나 지나치게 시험이나 학습에 대해 염려를 한다거나 하지는 않은가요? 당신은 난이도가 높은 문제들에 대해서는 제대로 시도도 해보지 않고 쉽게 포기하는 경향을 보입니다. 낮은 자아효능감으로 인해 학습전략의 수립이나 목표설정, 자기 통제력에서도 조금은 부족한 모습을 보이고 있지만 학력상승에 이것들은 반드시 필요한 것입니다. 자아효능감을 높이기 위해서는 스스로 당신의 능력을 믿어보시고 가치를 두는 것이 필요합니다. 그리고 부모님께서는 좀 더 학생에게 주의를 기울여 주는 것이 좋습니다. ‘열심히 해야겠구나’ 하는 식의 미래의 노력을 강조하는 격려의 말투도 좋지만 ‘열심히 잘 했구나’ 하는 식의 과거의 노력에 대한 칭찬의 말을 아낌없이 해주는 것이 좋습니다. 이런 칭찬들을 통한 주변의 도움과 학생 스스로의 노력이 있다면 충분히 자신에 대해 더욱 자신감을 지니고 좋은 성적을 거둘 수 있을 것입니다.";
        }

        return FactionDto.builder()
                .itemType(itemType)
                .resultType(resultType)
                .title(title)
                .score(score.get())
                .build();
    }

    public FactionDto cacluration2(List<SurveyResultEntity> surveyResultEntities){
        AtomicInteger score = new AtomicInteger();
        score.addAndGet(this.getSumScore(surveyResultEntities));

        String resultType;
        String itemType = "02";
        String title;


        if(5 < score.get()){
            resultType = "01";
            title = "학습 동기는 학습을 하는 이유가 됩니다. 높은 학습 동기를 지니고 있는 당신은 목표를 달성하기 위한 노력과 열정이 매우 높습니다. 또한 현재의 목표를 달성해 내기 위해 필요한 일들과 그렇지 않은 일들을 잘 구분하고 있으며 필요한 일들에 관심을 집중하여 관련된 정보를 자세하게 이해하고 있습니다. 학습동기에는 내적동기와 외적 동기가 있습니다. 내적 동기는 어떤 과제에 대해 본인이 가지는 흥미와 호기심, 자기만족감 및 성취감 등을 통한 동기이고, 외적 동기는 과제의 결과에 대한 물질적 보상이나 벌을 통한 동기를 의미합니다. 꾸준한 학습의지를 위해서는 내적 동기뿐만 아니라 외적 동기와의 적절한 조화가 필요합니다. 전체적으로 내적 동기와 외적 동기가 잘 조화를 이루고 있는 당신은 학습에 대한 높은 동기를 가지고 있습니다. 이렇듯 충분한 동기감에 대한 높은 성취감을 느낄 수 있도록 더욱 어려운 과제나 학습에 도전해보는 것도 필요합니다. 가지고 있는 동기에 걸맞은 성취감을 느꼈을 때 높은 학습동기를 계속 유지할 수 있으며 더 높은 학업성적도 얻을 수 있습니다. ";
        } else if(4 >= score.get() && score.get() <= 3) {
            resultType = "02";
            title = "학습 동기는 학습을 하는 이유가 됩니다. 보통 수준의 학습 동기를 지니고 있는 당신은 목표를 달성하기 위해서 노력도 하고 있으며 열정 또한 있습니다. 학습동기에는 내적 동기와 외적 동기가 있습니다. 내적 동기는 어떤 과제에 대해 본인이 가지는 흥미와 호기심, 자기만족감 및 성취감 등에서 비롯되는 것으로 결과에 대해 주어지는 보상에 관계없이 활동 자체나 성취감으로 보상받기 때문에 지속력이 강합니다. 이에 비해 외적 동기는 과제의 결과에 대한 물질적 보상이나 벌에서 비롯되는 동기를 의미하여 지속력이 약합니다. 꾸준한 학습의지를 위해서는 내재적 동기를 높여주는 것이 중요하지만 외적 동기를 유도하지 않고 순수하게 내적 동기만을 일으키는 것은 어려운 일입니다. 따라서 외적 동기를 일으켜 내적 동기를 자극할 필요가 있습니다. 적절한 수준의 학습 동기를 지니고 있는 당신이지만 더욱 더 학습에 대한 동기를 높여볼 필요가 있습니다. 자신이 혹시 내적 동기 없이 외적 동기에만 의해서 수동적으로 공부하고 있는 것은 아닌지, 아니면 내적 동기에 의해서만 공부를 하여 지칠 때가 있지는 않은지 살펴보는 기회를 가질 필요가 있습니다. 또한 자신의 목표를 좀 더 구체적으로 세워보고 목표에 대해 잘 계획하며 실천하고 있는지 항상 확인하는 습관을 길러 학습에 대한 동기를 더욱 높인다면 더 높은 성적을 거둘 수 있을 것입니다. ";
        } else {
            resultType = "03";
            title = "학습 동기는 공부를 하게 하는 의욕을 가져다 줍니다. 낮은 수준의 학습동기를 지니고 있는 당신은 전체적으로 학습에 대한 의욕이 낮기 때문에 학습에 대한 흥미를 지니고 있지 못한 상태입니다. 공부를 왜 해야 하는지에 대한 의문이 들거나 공부에 대해 전혀 무관심 하지는 않은가요? 무엇보다도 이러한 동기부족이 성적부진의 직접적인 원인이 될 수 있습니다. 학습동기에는 내적 동기와 외적 동기가 있습니다. 내적 동기는 어떤 과제에 대해 본인이 가지는 흥미와 호기심, 자기만족감 및 성취감 등에서 비롯되는 것으로 결과에 대해 주어지는 보상에 관계없이 활동 자체나 성취감으로 보상 받기 때문에 지속력이 강합니다. 이에 비해 외적 동기는 과제의 결과에 대한 물질적 보상이나 벌에서 비롯되는 동기를 의미하여 지속력이 약합니다. 꾸준한 학습의지를 위해서는 내재적 동기를 높여주는 것이 중요하지만 외적 동기를 유도하지 않고 순수하게 내적 동기만을 일으키는 것은 어려운 일입니다. 지금은 내적 동기와 외적 동기 모두가 많이 부족한 상태입니다. 학습에 대한 동기를 유발시키기 위해서는 성취감을 느껴보는 것이 중요합니다. 너무 어려운 과제를 선택하기보다는 쉬운 과제부터 기초를 탄탄히 하는 것이 중요합니다. 주위의 또래 친구들이나 선배들을 모델로 삼아 동기를 만들어보거나 자신의 꿈을 더 구체적으로 생각해보는 것도 동기를 만드는데 도움이 될 것입니다. 또한 부모님이나 선생님께서 학생에게 상이나 벌을 이용하여 외적 동기를 유발시켜주는 것도 좋은 방법이 됩니다. 상은 성공감과 만족감을 주어 학습을 촉진시켜 주고, 벌은 학생에 게 적절한 자극제가 되어 책임감을 심어줄 수 있습니다.";
        }

        return FactionDto.builder()
                .itemType(itemType)
                .resultType(resultType)
                .title(title)
                .score(score.get())
                .build();
    }

    public FactionDto cacluration3(List<SurveyResultEntity> surveyResultEntities){
        AtomicInteger score = new AtomicInteger();
        score.addAndGet(this.getSumScore(surveyResultEntities));

        String itemType = "03";
        String resultType;
        String title;

        if(5 < score.get()){
            resultType = "01";
            title = "학습 신념이 높은 당신은 자신이 공부하고 있는 내용들에 대해 높은 가치를 부여하고 있습니다. 학교수업이나 자신이 공부하고 있는 것들을 통해서 자신 스스로가 발전하고 있다고 생각하고 있습니다. 이는 공부를 하는 데에 매우 좋은 영향을 끼쳐서 높은 학업 성취를 가능하게 합니다. 현재 학교생활에도 대부분 만족하고 있으며 뚜렷한 자신만의 목표를 가지고 있습니다. 당신은 특히 복잡하고 어려운 문제를 해결해야 하는 상황에서 높은 학습신념으로 그 능력이 발휘됩니다. 하지만 학습신념이 높은데도 불구 하고  성적이 낮을 경우에는 다시 한 번 자신의 공부방법이나 습관들을 돌아볼 필요가 있습니다. 올바른 공부 방법을 택하고 있는지 생각해보고, 주변의 친구들은 어떻게 하고 있는지 한번쯤 검토해보는 것도 좋은 방법입니다. 현재 성적이 부진하다고 해도 당신은 높은 학습신념을 지니고 있기 때문에 좀 더 노력하여 실천 한다면 충분히 좋은 성적을 거둘 수 있을 것입니다.";
        } else if(4 >= score.get() && score.get() <= 3) {
            resultType = "02";
            title = "당신은 왜 공부해야 하는지에 대한 적절한 학습신념을 가지고 있습니다.   자신이 수업시간에 배우고 있는 내용이나 공부하는 내용들에 대해 어느 정도 가치를 두고 있습니다. 또한 공부를 통해 어느 정도 자신 스스로도 발전을 하고 있다고  생각하고 있습니다. 이런 것들은 공부를 하는데 좋은 자극이 되어 높은 학업 성취를 도와줍니다. 여기에 학습에 대한 신념을 조금 더 높여보는 것도 좋은 방법입니다. 학습을 통해서 자아발전을 이룰 수 있다는 사실을 믿고, 성적이 생각보다 잘 나오지 않아도 낙심하지 말고 꾸준히 실천한다면 학업능력이 더 좋아지고 좋은 성적도 얻을 수 있습니다. 조금만 더 자신이 하고 있는 공부에 대해 믿음을 가지고 긍정적으로 생각한다면 자신의 목표를 향해 한걸음 더 다가갈 수 있을 것입니다. ";
        } else {
            resultType = "03";
            title = "낮은 수준의 학습신념을 가지고 있는 당신은 공부하는 것에 대해 높은 의미를 부여하고 있지 않으며 자아발전에도 별로 도움이 되지 않는다고 생각하고 있습니다. 공부하는 것에 대해 무기력함을 느낄 때가 있지는 않은가요? 특히, 학습신념이 낮은 학생들 중에는 공부하는 능력은 선천적으로 결정되어 있어서 자신이 아무리 노력을 한다고 해도 성적이 더 이상 향상되지 않을 것이라 생각하는 경우도 있습니다. 이런 무기력한 모습 때문에 어려운 난이도의 문제를 접하였을 때 도전하지 않으며 심할 경우 학교와 학습에 대해 혐오하는 반응을 보일 때도 있습니다. 신념을 가지고 노력한다면 분명 공부하는 능력도 향상되고 성적도 오르며 당신에게도 큰 도움이 될 것입니다.     혹시, 당신이 성적이 높은 편임에도 불구하고 학습신념이 낮다면 자신에 대해 한번 돌아볼 필요가 있습니다. 고학년이 되어서 더욱 어려운 내용을 배우고 공부하게 될 때에는 신념 없이 단순히 공부만 하는 것은 쉬운 일이 아닙니다. 자신이 공부하는 내용에 대해 긍정적인 사고를 하고, 공부에 대한 목표와 동기부여를 하며, 어른이나 부모님들에게 조언을 구하여 학습신념을 높여본다면 꾸준히 높은 성적을 거둘 수 있을 것입니다.";
        }

        return FactionDto.builder()
                .itemType(itemType)
                .resultType(resultType)
                .title(title)
                .score(score.get())
                .build();
    }

    private Integer getSumScore(List<SurveyResultEntity> params) {
        AtomicInteger result = new AtomicInteger();
        params.forEach(surveyResultEntity -> {
            result.addAndGet(surveyResultEntity.getTotalScore());
        });
        return result.get();
    }

    private List<SurveyResultEntity> getResultBySubCode(
            final String code,
            final SurveyEntity surveyEntity
    ) {
        List<SurveyResultEntity> result = new ArrayList<>();

        List<SurveySubCategoryEntity> subCategoryEntities =
                surveyEntity.getSurveySubCategories().stream()
                .filter(t -> code.equals(t.getCode())).collect(Collectors.toList());


        for (SurveySubCategoryEntity subCategoryEntity : subCategoryEntities) {
            List<SurveyItemEntity> surveyItemEntities = subCategoryEntity.getSurveyItemEntities();

            for(SurveyItemEntity surveyItem: surveyItemEntities){
                result.addAll(surveyItem.getSurveyResultEntities());
            }
        }

        return result;
    }
}
