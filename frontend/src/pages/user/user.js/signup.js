import {mapActions, mapGetters} from 'vuex';
import {Tabs, TabPane, FormGroupInput, Button} from '@/components';
import {Alert} from '@/components'
export default {
    name: 'profile',
    bodyClass: 'profile-page',
    components: {
        Tabs,
        TabPane,
        [FormGroupInput.name]: FormGroupInput,
        [Button.name]: Button,
        Alert
    },
    data: function () {
        return {
            isBusiness: false,
            user: {
                userName: "",
                userId: "",
                userPass: "",
                userPhone: "",
                userEmail: "",
            },
            estate: {
                userName: "",
                userId: "",
                userPass: "",
                userPhone: "",
                userEmail: "",
                estateId:"",
            },
            errors: {
                requireID: true,
                maxID: true,
                requirePass: true,
                minPass: true,
                maxPass: true,
                checkPass: true,
                matchPass: true,
                requireName: true,
                checkName:true,
                requirePhone: true,
                matchPhone: true,
                requireEmail: true,
                matchEmail: true,
                checkEmail: false,
            },
            errors2: {
                requireNumber:true,
                requireID: true,
                maxID: true,
                requirePass: true,
                minPass: true,
                maxPass: true,
                checkPass: true,
                matchPass: true,
                requireName: true,
                checkName:true,
                requirePhone: true,
                matchPhone: true,
                requireEmail: true,
                matchEmail: true,
                checkEmail: false,
            },
            estateNumber:"",
            passwordcheck: ""
        };
    },
    computed: {
        ...mapGetters('user', ["getEstateInfo"]),

    },
    mounted(){
        this.click();
    },
    methods: {
        ...mapActions('user', ["requestRegister", "requestDuplicate", "requestEstate"]),
        clickRegisterMember() {
        for (var item in this.user) {
            if(this.user[item]==""){
                this.$alert("입력 정보를 다시 확인해주세요.");
                return;
            }
        }
        this.requestRegister(this.user);
        },
        clickRegisterEstate() {
        if(this.getEstateInfo==null){
            this.$alert("사업자 번호를 다시 확인해주세요.");
            return;
        }
        this.estate.estateId=this.getEstateInfo.id;
        for (var item in this.estate) {
            if(this.estate[item]==""){
                this.$alert("입력 정보를 다시 확인해주세요.");
                return;
            }
        }
        this.requestRegister(this.estate);
        },
        click() {
          this.passwordcheck='';
          for(var item in this.errors){
            this.errors[item]=true;
          }
          for(var item in this.errors2){
            this.errors2[item]=true;
          }
        },
        clickDuplicate(userId){
          this.requestDuplicate(userId);
        },
        clickCertification(number){
          this.requestEstate(number);
        },
        checkName(){
          if(this.user.userName==""){
            this.errors.requireName=false;
            this.errors.checkName=true;
            return;
          }else if (!/^[가-힣]{2,15}$/.test(this.user.userName)) {
            this.errors.requireName=true;
            this.errors.checkName=false;
            return;
        }else{
            this.errors.requireName=true;
            this.errors.checkName=true;
            return;
          }
        },
        checkID() {
            if (!this.user.userId) {
                this.errors.requireID = false;
                this.errors.maxID = true;
            } else if (this
                    .user
                    .userId
                    .length > 16) {
                this.errors.requireID = true;
                this.errors.maxID = false;
            } else {
                this.errors.requireID = true;
                this.errors.maxID = true;
            }
            return;
        },
        checkPass() {
            if (this.user.userPass == '') {
                this.errors.requirePass = false;
                this.errors.minPass = true;
                this.errors.maxPass = true;
                this.errors.matchPass = true;
                return;
            } else if (this
                    .user
                    .userPass
                    .length < 9) {
                this.errors.requirePass = true;
                this.errors.minPass = false;
                this.errors.maxPass = true;
                this.errors.matchPass = true;
            } else if (this
                    .user
                    .userPass
                    .length > 15) {
                this.errors.requirePass = true;
                this.errors.minPass = true;
                this.errors.maxPass = false;
                this.errors.matchPass = true;
            } else if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/.test(this.user.userPass)) {
                this.errors.requirePass = true;
                this.errors.minPass = true;
                this.errors.maxPass = true;
                this.errors.matchPass = false;
            } else {
                this.errors.requirePass = true;
                this.errors.minPass = true;
                this.errors.maxPass = true;
                this.errors.matchPass = true;
            }
            return;
        },
        matchPass() {
            if (this.user.userPass != this.passwordcheck) {
                this.errors.checkPass = false;
                return;
            } else {
                this.errors.checkPass = true;
            }
        },
        checkEmail() {
            if (!this.user.userEmail) {
                this.errors.requireEmail = false;
                this.errors.matchEmail = true;
                return;
            } else if (!/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.user.userEmail)) {
                this.errors.requireEmail = true;
                this.errors.matchEmail = false;
                return;
            } else {
                this.errors.requireEmail = true;
                this.errors.matchEmail = true;
                return;
            }
        },
        checkPhone() {
            if (!this.user.userPhone) {
                this.errors.requirePhone = false;
                this.errors.matchPhone = true;
                return;
            } else if (!/01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/.test(this.user.userPhone)) {
                this.errors.requirePhone = true;
                this.errors.matchPhone = false;
                return;
            } else {
                this.errors.requirePhone = true;
                this.errors.matchPhone = true;
                return;
            }
        },
        checkName2(){
            if(this.estate.userName==""){
                this.errors2.requireName=false;
                this.errors2.checkName=true;
                return;
              }else if (!/^[가-힣]{2,15}$/.test(this.estate.userName)) {
                this.errors2.requireName=true;
                this.errors2.checkName=false;
                return;
            }else{
                this.errors2.requireName=true;
                this.errors2.checkName=true;
                return;
              }
          },
          checkID2() {
              if (!this.estate.userId) {
                  this.errors2.requireID = false;
                  this.errors2.maxID = true;
              } else if (this
                      .estate
                      .userId
                      .length > 16) {
                  this.errors2.requireID = true;
                  this.errors2.maxID = false;
              } else {
                  this.errors2.requireID = true;
                  this.errors2.maxID = true;
              }
              return;
          },
          checkPass2() {
              if (this.estate.userPass == '') {
                  this.errors2.requirePass = false;
                  this.errors2.minPass = true;
                  this.errors2.maxPass = true;
                  this.errors2.matchPass = true;
                  return;
              } else if (this
                      .estate
                      .userPass
                      .length < 9) {
                  this.errors2.requirePass = true;
                  this.errors2.minPass = false;
                  this.errors2.maxPass = true;
                  this.errors2.matchPass = true;
              } else if (this
                      .estate
                      .userPass
                      .length > 15) {
                  this.errors2.requirePass = true;
                  this.errors2.minPass = true;
                  this.errors2.maxPass = false;
                  this.errors2.matchPass = true;
              } else if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/.test(this.estate.userPass)) {
                  this.errors2.requirePass = true;
                  this.errors2.minPass = true;
                  this.errors2.maxPass = true;
                  this.errors2.matchPass = false;
              } else {
                  this.errors2.requirePass = true;
                  this.errors2.minPass = true;
                  this.errors2.maxPass = true;
                  this.errors2.matchPass = true;
              }
              return;
          },
          matchPass2() {
              if (this.estate.userPass != this.passwordcheck) {
                  this.errors2.checkPass = false;
                  return;
              } else {
                  this.errors2.checkPass = true;
              }
          },
          checkEmail2() {
              if (!this.estate.userEmail) {
                  this.errors2.requireEmail = false;
                  this.errors2.matchEmail = true;
                  return;
              } else if (!/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.estate.userEmail)) {
                  this.errors2.requireEmail = true;
                  this.errors2.matchEmail = false;
                  return;
              } else {
                  this.errors2.requireEmail = true;
                  this.errors2.matchEmail = true;
                  return;
              }
          },
          checkPhone2() {
              if (!this.estate.userPhone) {
                  this.errors2.requirePhone = false;
                  this.errors2.matchPhone = true;
                  return;
              } else if (!/01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/.test(this.estate.userPhone)) {
                  this.errors2.requirePhone = true;
                  this.errors2.matchPhone = false;
                  return;
              } else {
                  this.errors2.requirePhone = true;
                  this.errors2.matchPhone = true;
                  return;
              }
          },
          checkNumber(){
            if(!this.estateNumber){
                this.errors2.requireNumber=false;
                return;
            }else{
                this.errors2.requireNumber=true;
              return;
            }
          }
    }
};
