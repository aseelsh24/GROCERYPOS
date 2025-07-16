FROM openjdk:11-jdk

# Install required packages
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Download and install Android SDK
ENV ANDROID_SDK_ROOT /opt/android-sdk
RUN mkdir -p ${ANDROID_SDK_ROOT}

# Download Android SDK tools
RUN curl -o sdk-tools.zip https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip \
    && unzip sdk-tools.zip -d ${ANDROID_SDK_ROOT} \
    && rm sdk-tools.zip

# Set SDK manager path
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin

# Accept licenses and install required SDK packages
RUN yes | sdkmanager --licenses \
    && sdkmanager "platform-tools" \
        "platforms;android-34" \
        "build-tools;34.0.0"

WORKDIR /app
COPY . .

# Build the APK
CMD ["./gradlew", "assembleRelease"]
