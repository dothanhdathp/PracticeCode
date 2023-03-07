function gg_trans {
  sl=$1
  shift
  tl=$1
  shift
  ### shift
  base_url="https://translate.googleapis.com/translate_a/single?client=gtx&sl=${sl}&tl=${tl}&dt=t&q="
  ua='Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/109.0'
  qry=$( echo "${@}" | sed -e "s/\ /+/g" )
  full_url=${base_url}${qry}
  ### echo curl -sA ${ua} ${full_url}
  response=$(curl -sA "${ua}" "${full_url}")
  echo ${response}  | sed 's/","/\n/g' | sed -E 's/\[|\]|"//g' | head -1
}
alias trans="gg_trans en vi "