<template>
  <div>
    <template v-if="inputData.indexOf(refTag) === -1 && type == 'password'">
      <a-input-password
        v-model:value="inputData"
        :placeholder="placeholder"
        :disabled="!!selectData"
        @change="inputChange"
      >
        <template #addonBefore>
          <a-tooltip>
            <template #title>
              引用工作空间环境变量可以方便后面多处使用相同的密码统一修改

              <ul v-if="!envList.length">
                当前没有可以引用的环境变量
              </ul>
            </template>
            <a-select v-model:value="selectData" placeholder="引用环境变量" style="width: 120px" @change="selectChange">
              <a-select-option value="">不引用环境变量</a-select-option>
              <a-select-option v-for="item in envList" :key="item.id" :value="item.name"
                >{{ item.name }}
              </a-select-option>
            </a-select>
          </a-tooltip>
        </template>
      </a-input-password>
    </template>
    <template v-else>
      <a-input v-model:value="inputData" :placeholder="placeholder" :disabled="!!selectData" @change="inputChange">
        <template #addonBefore>
          <a-tooltip>
            <template #title>
              引用工作空间环境变量可以方便后面多处使用相同的密码统一修改
              <ul v-if="!envList.length">
                当前没有可以引用的环境变量
              </ul>
            </template>
            <a-select v-model:value="selectData" placeholder="引用环境变量" style="width: 120px" @change="selectChange">
              <a-select-option value="">引用环境变量</a-select-option>
              <a-select-option v-for="item in envList" :key="item.id" :value="item.name"
                >{{ item.name }}
              </a-select-option>
            </a-select>
          </a-tooltip>
        </template>
      </a-input>
    </template>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    input: {
      type: String,
      default: ''
    },
    envList: {
      type: Array,
      default: () => []
    },
    placeholder: {
      type: String,
      default: '请输入...'
    },
    type: {
      type: String,
      default: 'password'
    }
  },
  emits: ['change'],
  data() {
    return {
      inputData: '',
      refTag: '$ref.wEnv.',
      selectData: null
    }
  },
  watch: {
    input: {
      deep: true,

      handler(v) {
        this.inputData = v
        if (v.indexOf(this.refTag) == -1) {
          this.selectData = null
        } else {
          // this.selectData = v.replace(this.refTag)
        }
      },

      immediate: true
    }
  },
  methods: {
    selectChange(v) {
      this.selectData = v
      const newV = v ? this.refTag + v : ''
      this.$emit('change', newV)
    },
    inputChange() {
      this.$emit('change', this.inputData)
    }
  }
}
</script>
