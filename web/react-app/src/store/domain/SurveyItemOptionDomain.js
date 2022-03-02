import {action, makeAutoObservable, observable} from "mobx";

export class SurveyItemOptionDomain {
  id = ""
  title = ""
  score = 0
  orderNum = 0
  useYn = 'Y'
  regDt = null
  editDt = null
  delDt = null

  result = ""

  _isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id:observable,
      _isChecked: observable,
      setIsChecked : action,
    })
    this.store = store

    this.id = item.id
    this.title = item.title
    this.score = item.score
    this.orderNum = item.orderNum
    this.useYn = item.useYn
    this.regDt = item.regDt
    this.editDt = item.editDt
    this.delDt = item.delDt
  }

  set isChecked (param) {
    this._isChecked = param
  }

  setUnCheckAll(){
    this.store.uncheckAll()
  }

  setIsChecked(param) {
    this._isChecked = param
  }

  get isChecked () {
    return this._isChecked
  }

  get resultJson () {
    return {
      surveyItemOptionId: this.id,
      checked: this.isChecked,
    }
  }


}